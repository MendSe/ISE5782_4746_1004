package renderer;

import geometries.Intersectable;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for integration test of Camera
 */
public class CameraRayIntersectionTest {
    Camera camera = new Camera(new Point(1, 1.5, 0), new Vector(1, 0, 0), new Vector(0, 1, 0));

    /**
     * Help function to count the number of intersections between camera's Rays and objects from intersectable
     *
     * @param camera camera p0 and vTo vUp
     * @param o      objects from intersectable
     * @param expect expected number of points
     */
    private void countIntersections(Camera camera, Intersectable o, int expect) {
        camera.setVPSize(3, 3);
        camera.setVPDistance(1);
        int number = 0;
        List<Point> intersections = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> inter = o.findIntersections(camera.constructRay(3, 3, j, i).get(40));
                if (inter != null)
                    number += inter.size();
            }
        }
        if (intersections != null) {
            number = intersections.size();
        }
        assertEquals(expect, number, "Bad number of intersection points");
    }

    /**
     * Test for function constructRay with spheres
     */
    @Test
    public void cameraSphereIntersections() {

        //TC01:Normal Sphere
        countIntersections(camera, new Sphere(new Point(2, 2, 0.5), 1), 8);

        //TC02:1 pixel sphere
        countIntersections(camera, new Sphere(new Point(2, 1.5, 0), 0.5), 2);

        //TC03: Big sphere
        countIntersections(camera, new Sphere(new Point(4.5, 1.5, 0), 3), 18);

        //TC04: In the sphere
        countIntersections(camera, new Sphere(new Point(1, 1.5, 0), 3), 9);

        //TC05: Sphere not in the camera's view
        countIntersections(camera, new Sphere(new Point(-1, -1.5, 0), 1), 0);
    }

    /**
     * Test for function constructRay with planes
     */
    @Test
    public void cameraPlaneIntersections() {
        //TC01: Normal plane in front of camera
        countIntersections(camera, new Plane(new Point(3, 0, 0), new Vector(1, 0, 0)), 9);

        //TC02: Plane under camera
        countIntersections(camera, new Plane(new Point(0, -3, 0), new Vector(0, 1, 0)), 3);

        //TC03:Plane not in the camera's view
        countIntersections(camera, new Plane(new Point(-3, 0, 0), new Vector(1, 0, 0)), 0);
    }

    /**
     * Test for function constructRay with triangles
     */
    @Test
    public void cameraTriangleIntersections() {

        //TC01: Normal triangle
        countIntersections(camera, new Triangle(new Point(2, 1, 0.5), new Point(2, 1, -0.5), new Point(2, 3, 0)), 2);
    }
}
