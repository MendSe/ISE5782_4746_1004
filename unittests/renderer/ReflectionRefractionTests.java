/**
 *
 */
package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(0.5)));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();//
        camera.writeToImage();
    }

    /**
     * Personal test for multiple objects and shadows
     */
    @Test
    public void PersonalTest() {
        Camera camera = new Camera(new Point(-650, -400, 150), new Vector(6, 4, -1), new Vector(6, 4, 52)) //
                .setVPSize(200, 200).setVPDistance(1000);

        Scene scene = new Scene.Builder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))) //
                .setBackground("resources/fond.png", 801, 800)
                .build();
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //

                new Cylinder(new Ray(new Point(0, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(100)),
                new Cylinder(new Ray(new Point(10, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(20, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(30, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(40, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-10, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-20, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-30, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-40, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),

                new Polygon(new Point(-50, 0, 0), new Point(100, 0, 0), new Point(100, 100, 0), new Point(-50, 100, 0)).setEmission(new Color(BLACK))    //fond
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20)),

                new Polygon(new Point(-43, 0, 0), new Point(43, 0, 0), new Point(43, 50, 0), new Point(-43, 50, 0)).setEmission(new Color(10, 30, 0))    //sous les roues
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(50, 0, 10), new Point(50, 50, 10), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//sur les roues
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-43, 0, 0), new Point(-50, 0, 10), new Point(-50, 50, 10), new Point(-43, 50, 0)).setEmission(new Color(BLACK))//gauche
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30)),
                new Polygon(new Point(43, 0, 0), new Point(43, 50, 0), new Point(50, 50, 10), new Point(50, 0, 10)).setEmission(new Color(BLACK))//droite
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30)),
                new Polygon(new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 50, 20), new Point(-50, 50, 20)).setEmission(new Color(10, 30, 0))// au dessus
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 0, 10)).setEmission(new Color(10, 30, 0))//devant
                        .setMaterial(new Material()
                                .setKd(0.5)
                                .setKs(0.25)
                                .setShininess(30)),
                new Polygon(new Point(-50, 50, 10), new Point(-50, 50, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//derriere
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(-50, 50, 20), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//a gauche
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(50, 0, 10), new Point(50, 0, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//a droite
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(40, 10, 35), new Point(40, 40, 35), new Point(10, 45, 35), new Point(-30, 35, 35), new Point(-30, 15, 35), new Point(10, 5, 35)).setEmission(new Color(10, 30, 0))//a droite
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(40, 10, 20), new Point(40, 10, 35), new Point(40, 40, 35), new Point(40, 40, 20)).setEmission(new Color(10, 30, 0))//1
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(40, 40, 20), new Point(40, 40, 35), new Point(10, 45, 35), new Point(10, 45, 20)).setEmission(new Color(10, 30, 0))//2
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(10, 45, 20), new Point(10, 45, 35), new Point(-30, 35, 35), new Point(-30, 35, 20)).setEmission(new Color(10, 30, 0))//3
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-30, 35, 20), new Point(-30, 35, 35), new Point(-30, 15, 35), new Point(-30, 15, 20)).setEmission(new Color(10, 30, 0))//4
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-30, 15, 20), new Point(-30, 15, 35), new Point(10, 5, 35), new Point(10, 5, 20)).setEmission(new Color(10, 30, 0))//5
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(10, 5, 20), new Point(10, 5, 35), new Point(40, 10, 35), new Point(40, 10, 20)).setEmission(new Color(10, 30, 0))//6
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),

                new Cylinder(new Ray(new Point(-30, 25, 27.5), new Vector(-1, 0, 0)), 5d, 30).setEmission(new Color(10, 30, 0))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)),
                new Cylinder(new Ray(new Point(-60, 25, 27.5), new Vector(-1, 0, 0)), 4d, 25).setEmission(new Color(10, 30, 0))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)),

                new Cylinder(new Ray(new Point(-75, 25, 27.5), new Vector(-1, 0, 0)), 2d, 20).setEmission(new Color(50, 50, 10))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Sphere(new Point(-95, 25, 27.5), 2d).setEmission(new Color(50, 50, 10))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20)),

                new Polygon(new Point(-60, 100, 0), new Point(120, 100, 0), new Point(120, 100, 23.3), new Point(-60, 100, 23.3)).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.4).setShininess(30)),
                new Polygon(new Point(-60, 100, 23.3), new Point(120, 100, 23.3), new Point(120, 100, 46.6), new Point(-60, 100, 46.6)).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setShininess(30)),
                new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.4).setShininess(30))

        );
        scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point(-200, -100, 30), new Vector(1, 0.5, 0)) //
                .setKl(0.00004).setKq(0.000005));


        ImageWriter imageWriter = new ImageWriter("PersonalTest", 800, 800);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();//
        camera.writeToImage();
    }
}

