package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test for Cylinder
 */
class CylinderTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test of normal on the first disk
        Cylinder cy1 = new Cylinder(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 2d, 1d);
        assertEquals(new Vector(1, 0, 0), cy1.getNormal(new Point(2, 0, 0)),
                "Bad normal to Cylinder when point is on the first disk");

        //Test of normal on the first disk
        Cylinder cy2 = new Cylinder(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 2d, 1d);
        assertEquals(new Vector(1, 0, 0), cy2.getNormal(new Point(2, 1, 0)),
                "Bad normal to Cylinder when point is on the second disk");

        //Test of normal on the first disk
        Cylinder cy3 = new Cylinder(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d, 2d);
        assertEquals(new Vector(1, 0, 0), cy3.getNormal(new Point(2, 1, 0)),
                "Bad normal to Cylinder when point is on the tube of the cylinder");


    }
}