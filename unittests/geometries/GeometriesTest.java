package geometries;

import primitives.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test for Geometries
 */
public class GeometriesTest {
    @Test
    void findIntsersections() {
        Sphere sp = new Sphere(new Point(2, 2, 0), 1);
        Triangle tg = new Triangle(new Point(3, 2, 1), new Point(3, 4, 0), new Point(3, 2, -1));
        Plane pln = new Plane(new Point(4, 1, 1), new Point(4, 3, 0), new Point(4, 1, -1));
        Geometries collection = new Geometries(sp, tg, pln);
        Geometries emptycoll = new Geometries();

        // ============ Equivalence Partitions Tests ==============
        //TC:01 Normal tests
        List<Point> result = collection.findIntsersections(new Ray(new Point(2, 2, 0), new Vector(5, -5, 0)));
        assertEquals(2, result.size(), "Wrong number of intersections");

        // =============== Boundary Values Tests ==================
        //TC:02 Empty collection
        assertNull(emptycoll.findIntsersections(new Ray(new Point(0, 2, 0), new Vector(5, 0, 0))), "Bad number of intersections when empty collection");

        //TC:03 No intersections
        assertNull(emptycoll.findIntsersections(new Ray(new Point(0, 2, 0), new Vector(-5, 0, 0))), "Bad number of intersections when No intersections");

        //TC:04 only one object intersected
        result = collection.findIntsersections(new Ray(new Point(-1, -1, 0), new Vector(5, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of intersections");

        //TC:05 all objects intersected
        result = collection.findIntsersections(new Ray(new Point(0, 2.5, 0), new Vector(5, 0, 0)));
        assertEquals(4, result.size(), "Wrong number of intersections");
    }
}
