package geometries;

import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test for Plane
 */
class PlaneTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //EP:01 Normal test
        try {
            Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }
        // =============== Boundary Values Tests ==================
        //BVA:01 Plane constructor with 2 equal points
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0)),
                "Constructed a plane with 2 equal points");

        //BVA:02 Plane constructor with 3 points on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 1, 2), new Point(2, 2, 4), new Point(4, 4, 8)),
                "Constructed a plane with 3 points on the same line");
    }

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC normal test
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }

    @Test
    public void testfindIntersection() {
        Ray ray;
        Plane pln = new Plane(new Point(1, 0, 0), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        //TC01: Intersection with the plane
        ray = new Ray(new Point(0, 1, 1), new Vector(0, 0, -1));
        assertEquals(new Point(0, 1, 0), pln.findIntersections(ray).get(0),
                "Bad Intersection");

        //TC02: No intersection between the ray and the plane
        ray = new Ray(new Point(1, 0, 0), new Vector(1, 1, 1));
        assertNull(pln.findIntersections(ray), "Wrong intersection");

        // =============== Boundary Values Tests ==================
        //TC03: Ray is parallel to plane
        ray = new Ray(new Point(1, 1, 1), new Vector(-1, 1, 0));
        assertNull(pln.findIntersections(ray), "Wrong intersection");

        //TC04: Ray is in the plane
        ray = new Ray(new Point(0, 0, 0), new Vector(-1, 1, 0));
        assertNull(pln.findIntersections(ray),
                "Ray isn't supposed to be in the plane");

        //Group: Ray is orthogonal to the plane
        //TC05: Ray is before the plane
        ray = new Ray(new Point(.5, 0, 0), new Vector(1, 1, 1));
        assertEquals(new Point(2d / 3, 0.5d / 3, 0.5d / 3), pln.findIntersections(ray).get(0),
                "Bad intersection");

        //TC06: Ray is before the plane
        ray = new Ray(new Point(1d / 3, 1d / 3, 1d / 3), new Vector(1, 1, 1));
        assertNull(pln.findIntersections(ray), "Ray inside the plan");

        //TC07: Ray is after the plane
        ray = new Ray(new Point(2, 0, 0), new Vector(2, 2, 2));
        assertNull(pln.findIntersections(ray), "The ray is after the plane");

        //TC08: Ray is the plane but isn't parallel nor orthogonal
        ray = new Ray(new Point(1, -1, 1), new Vector(2, 3, 4));
        assertNull(pln.findIntersections(ray), "Not orthogonal/parallel");

        //TC09: Ray starts on the same point as the plane but isn't parallel nor orthogonal
        assertNull(pln.findIntersections(new Ray(new Point(1, 0, 0), new Vector(2, 3, 4))),
                "Same origin but not parallel nor orthogonal");
    }
}