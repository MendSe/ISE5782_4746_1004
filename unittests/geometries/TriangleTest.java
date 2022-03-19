package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test for triangle
 */
class TriangleTest {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // normal of triangle
        Triangle tg = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3),tg.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }

    @Test
    public void testFindIntersection()
    {
        Triangle trg = new Triangle(new Point(1,0,0),new Point(0,1,0),new Point(0,0,1));
        Plane pln = new Plane(new Point(1,0,0), new Point(0,1,0),new Point(0,0,1));
        Ray ray;

        // ==================Equivalence Partition Tests=================
        //TC01:Vertex


        //TC03: Inside triangle
        ray = new Ray (new Point (1,1,1),new Vector(-1,-1,-1));
        assertEquals(List.of(new Point(1d/3,1d/3,1d/3)),trg.findIntsersections(ray),"Bad Intersection");

        // ===============Boundary Tests Value============
        //TC04: In vertex
        ray = new Ray(new Point(1,1,0),new Vector(-1,-1,0));
        assertEquals(List.of(new Point(0.5,0.5,0)),trg.findIntsersections(ray),
                "Bad Intersection");

        //TC05: Edge
        ray = new Ray(new Point (2,0,0),new Vector(-1,0,0));
        assertEquals(List.of(new Point(1,0,0)),trg.findIntsersections(ray),
                "Bad Intersection");


    }
}