/**
 *
 */
package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 *
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }
        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }
    @Test
    public void testfindIntersection() {

        Polygon polygon = new Polygon(new Point(-0.5, -0.5, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        String error = "ERROR: geometries.Polygon.findIntersections() -- bad result";


        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's inside
        assertEquals(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0, -1))), List.of(new Point(0.5, 0.25, 0)), error);

        // TC02: Ray's outside, against Vertex
        assertNull(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(1.5, -0.5, -1))), error);

        // TC03: Ray's outside, against Edge
        assertNull(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(0.75, 0.75, -1))), error);


        // =============== Boundary Values Tests ==================
        // TC11: Ray intersects Vertex
        assertNull(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(-0.25, 0.75, -1))), error);

        // TC12: Ray intersects Edge
        assertNull(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0.25, -1))), error);

        // TC13: Ray intersects on Edge
        assertNull(polygon.findIntersections(new Ray(new Point(0.25, 0.25, 1), new Vector(-1.25, -2.25, -1))), error);

    }
}