package special;

import geometries.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Class BVHTest for the manual and automatic hierarchy of BVH
 */
public class BVHTest {
    /**
     * Manual Hierarchy of Bonding Box with multithreading
     */
    @Test
    public void PersonalTestManual() {
        Camera camera = new Camera(new Point(-650, -400, 150), new Vector(6, 4, -1), new Vector(6, 4, 52)) //
                .setVPSize(200, 200).setVPDistance(1000);

        Scene scene = new Scene.Builder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))) //
                .setBackground("resources/fond.png", 801, 800)
                .build();
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                        new Cylinder(new Ray(new Point(0, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(100)),
                new Cylinder(new Ray(new Point(10, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30))),
                new BVHNode(
                new Cylinder(new Ray(new Point(20, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(30, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)))),
                new BVHNode(
                new BVHNode(
                new Cylinder(new Ray(new Point(40, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-10, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30))),
                new BVHNode(
                new Cylinder(new Ray(new Point(-20, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Point(-30, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30))))),
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new Cylinder(new Ray(new Point(-40, 0, 5), new Vector(0, 1, 0)), 5d, 50).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30)),

                new Polygon(new Point(-50, 0, 0), new Point(100, 0, 0), new Point(100, 100, 0), new Point(-50, 100, 0)).setEmission(new Color(BLACK))    //ground
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20))),
                new BVHNode(

                new Polygon(new Point(-43, 0, 0), new Point(43, 0, 0), new Point(43, 50, 0), new Point(-43, 50, 0)).setEmission(new Color(10, 30, 0))    //under wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(50, 0, 10), new Point(50, 50, 10), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//on wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)))),
                new BVHNode(
                new BVHNode(
                new Polygon(new Point(-43, 0, 0), new Point(-50, 0, 10), new Point(-50, 50, 10), new Point(-43, 50, 0)).setEmission(new Color(BLACK))//left to wheels
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30)),
                new Polygon(new Point(43, 0, 0), new Point(43, 50, 0), new Point(50, 50, 10), new Point(50, 0, 10)).setEmission(new Color(BLACK))//right to wheels
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30))),
                new BVHNode(
                new Polygon(new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 50, 20), new Point(-50, 50, 20)).setEmission(new Color(10, 30, 0))// above wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 0, 10)).setEmission(new Color(10, 30, 0))//in front
                        .setMaterial(new Material()
                                .setKd(0.5)
                                .setKs(0.25)
                                .setShininess(30)))))),
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new Polygon(new Point(-50, 50, 10), new Point(-50, 50, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//behind
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(-50, 50, 20), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//left side
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60))),
                new BVHNode(
                new Polygon(new Point(50, 0, 10), new Point(50, 0, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//right side
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(40, 10, 35), new Point(40, 40, 35), new Point(10, 45, 35), new Point(-30, 35, 35), new Point(-30, 15, 35), new Point(10, 5, 35)).setEmission(new Color(10, 30, 0))//6 vertices polygon
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)))),
                new BVHNode(
                new BVHNode(
                new Polygon(new Point(40, 10, 20), new Point(40, 10, 35), new Point(40, 40, 35), new Point(40, 40, 20)).setEmission(new Color(10, 30, 0))//1
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Polygon(new Point(40, 40, 20), new Point(40, 40, 35), new Point(10, 45, 35), new Point(10, 45, 20)).setEmission(new Color(10, 30, 0))//2
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60))),
                new BVHNode(
                new Polygon(new Point(10, 45, 20), new Point(10, 45, 35), new Point(-30, 35, 35), new Point(-30, 35, 20)).setEmission(new Color(10, 30, 0))//3
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-30, 35, 20), new Point(-30, 35, 35), new Point(-30, 15, 35), new Point(-30, 15, 20)).setEmission(new Color(10, 30, 0))//4
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60))))),
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new Polygon(new Point(-30, 15, 20), new Point(-30, 15, 35), new Point(10, 5, 35), new Point(10, 5, 20)).setEmission(new Color(10, 30, 0))//5
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(10, 5, 20), new Point(10, 5, 35), new Point(40, 10, 35), new Point(40, 10, 20)).setEmission(new Color(10, 30, 0))//6
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60))),
                new BVHNode(

                new Cylinder(new Ray(new Point(-30, 25, 27.5), new Vector(-1, 0, 0)), 5d, 30).setEmission(new Color(10, 30, 0))// big canon
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)),
                new Cylinder(new Ray(new Point(-60, 25, 27.5), new Vector(-1, 0, 0)), 4d, 25).setEmission(new Color(10, 30, 0))// 2nd canon
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)))),
                new BVHNode(
                new BVHNode(

                new Cylinder(new Ray(new Point(-75, 25, 27.5), new Vector(-1, 0, 0)), 2d, 20).setEmission(new Color(50, 50, 10))//  rocket
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Sphere(new Point(-95, 25, 27.5), 2d).setEmission(new Color(50, 50, 10))// rocket end
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20))),
                new BVHNode(

                new Polygon(new Point(-60, 100, 0), new Point(120, 100, 0), new Point(120, 100, 23.3), new Point(-60, 100, 23.3)).setEmission(new Color(RED)) //flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30)),
                new Polygon(new Point(-60, 100, 23.3), new Point(120, 100, 23.3), new Point(120, 100, 46.6), new Point(-60, 100, 46.6)).setEmission(new Color(BLUE)) //flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))))))),
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new BVHNode(
                new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30)), new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))), new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))), new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))), new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))), new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30))))

        );
        scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point(-200, -100, 30), new Vector(1, 0.5, 0)) //
                .setKl(0.00004).setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(30, 150, 30), new Point(0, 0, 70), new Vector(0, 0, -1)) //
                .setKl(0.00004).setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-30.2, 25,27.5), new Vector(-1, 0, 0)) //
                .setKl(0.004).setKq(0.01));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-60.2, 25,27.5), new Vector(-1, 0, 0)) //
                .setKl(0.04).setKq(0.01));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-64, 25,27.5), new Vector(1, 0, 0)) //
                .setKl(0.4).setKq(0.05));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-34, 25,27.5), new Vector(1, 0, 0)) //
                .setKl(0.4).setKq(0.05));
        // scene.lights.add(new PointLight(new Color(800, 50, 0), new Point(-60, 25, 27.5)) //
        //       .setKl(0.00004).setKq(0.000005));


        ImageWriter imageWriter = new ImageWriter("PersonalTestBVH", 800, 800);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene))
                .setMultithreading(3)
                .setDebugPrint(0.1)//
                .renderImage();
        camera.writeToImage();
    }

    /**
     * Automatic hierarchy of BVH with multithreading
     */
    @Test
    public void PersonalTestAuto() {
        Camera camera = new Camera(new Point(-650, -400, 150), new Vector(6, 4, -1), new Vector(6, 4, 52)) //
                .setVPSize(200, 200).setVPDistance(1000);

        Scene scene = new Scene.Builder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))) //
                .setBackground("resources/fond.png", 801, 800)
                .build();
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( new BVH(//

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

                new Polygon(new Point(-50, 0, 0), new Point(100, 0, 0), new Point(100, 100, 0), new Point(-50, 100, 0)).setEmission(new Color(BLACK))    //ground
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20)),

                new Polygon(new Point(-43, 0, 0), new Point(43, 0, 0), new Point(43, 50, 0), new Point(-43, 50, 0)).setEmission(new Color(10, 30, 0))    //under wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(50, 0, 10), new Point(50, 50, 10), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//on wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-43, 0, 0), new Point(-50, 0, 10), new Point(-50, 50, 10), new Point(-43, 50, 0)).setEmission(new Color(BLACK))//left to wheels
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30)),
                new Polygon(new Point(43, 0, 0), new Point(43, 50, 0), new Point(50, 50, 10), new Point(50, 0, 10)).setEmission(new Color(BLACK))//right to wheels
                        .setMaterial(new Material().setKd(0.05).setKs(0.25).setShininess(30)),
                new Polygon(new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 50, 20), new Point(-50, 50, 20)).setEmission(new Color(10, 30, 0))// above wheels
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(50, 0, 20), new Point(50, 0, 10)).setEmission(new Color(10, 30, 0))//in front
                        .setMaterial(new Material()
                                .setKd(0.5)
                                .setKs(0.25)
                                .setShininess(30)),
                new Polygon(new Point(-50, 50, 10), new Point(-50, 50, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//behind
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(-50, 0, 10), new Point(-50, 0, 20), new Point(-50, 50, 20), new Point(-50, 50, 10)).setEmission(new Color(10, 30, 0))//left side
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(50, 0, 10), new Point(50, 0, 20), new Point(50, 50, 20), new Point(50, 50, 10)).setEmission(new Color(10, 30, 0))//right side
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(60)),
                new Polygon(new Point(40, 10, 35), new Point(40, 40, 35), new Point(10, 45, 35), new Point(-30, 35, 35), new Point(-30, 15, 35), new Point(10, 5, 35)).setEmission(new Color(10, 30, 0))//6 vertices polygon
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

                new Cylinder(new Ray(new Point(-30, 25, 27.5), new Vector(-1, 0, 0)), 5d, 30).setEmission(new Color(10, 30, 0))// big canon
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)),
                new Cylinder(new Ray(new Point(-60, 25, 27.5), new Vector(-1, 0, 0)), 4d, 25).setEmission(new Color(10, 30, 0))// 2nd canon
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(30).setKt(0.4)),

                new Cylinder(new Ray(new Point(-75, 25, 27.5), new Vector(-1, 0, 0)), 2d, 20).setEmission(new Color(50, 50, 10))//  rocket
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Sphere(new Point(-95, 25, 27.5), 2d).setEmission(new Color(50, 50, 10))// rocket end
                        .setMaterial(new Material().setKd(0.5).setKs(0.25).setShininess(20)),

                new Polygon(new Point(-60, 100, 0), new Point(120, 100, 0), new Point(120, 100, 23.3), new Point(-60, 100, 23.3)).setEmission(new Color(RED)) //flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30)),
                new Polygon(new Point(-60, 100, 23.3), new Point(120, 100, 23.3), new Point(120, 100, 46.6), new Point(-60, 100, 46.6)).setEmission(new Color(BLUE)) //flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30)),
                new Polygon(new Point(-60, 100, 46.6), new Point(120, 100, 46.6), new Point(120, 100, 70), new Point(-60, 100, 70)).setEmission(new Color(WHITE)) // flag
                        .setMaterial(new Material().setKd(0.4).setShininess(30)))

        );
        scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point(-200, -100, 30), new Vector(1, 0.5, 0)) //
                .setKl(0.00004).setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(30, 150, 30), new Point(0, 0, 70), new Vector(0, 0, -1)) //
                .setKl(0.00004).setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-30.2, 25,27.5), new Vector(-1, 0, 0)) //
                .setKl(0.004).setKq(0.01));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-60.2, 25,27.5), new Vector(-1, 0, 0)) //
                .setKl(0.04).setKq(0.01));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-64, 25,27.5), new Vector(1, 0, 0)) //
                .setKl(0.4).setKq(0.05));
        scene.lights.add(new SpotLight(new Color(5000, 50, 0), new Point(-34, 25,27.5), new Vector(1, 0, 0)) //
                .setKl(0.4).setKq(0.05));
        // scene.lights.add(new PointLight(new Color(800, 50, 0), new Point(-60, 25, 27.5)) //
        //       .setKl(0.00004).setKq(0.000005));


        ImageWriter imageWriter = new ImageWriter("PersonalTestAuto", 800, 800);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage();//
        camera.writeToImage();
    }
}
