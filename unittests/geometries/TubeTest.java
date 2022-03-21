package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class test for Tube
 */
class TubeTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test of normal on the Tube
        Tube tb0 = new Tube(new Ray(new Point(1, 0, 0), new Vector(0, 2, 0)), 1d);
        assertEquals(new Vector(1, 0, 0), tb0.getNormal(new Point(2, 1, 0)),
                "Bad normal to Tube");

        // =============== Boundary Values Tests ==================
        //Test of normal when the connection between the point on the body and the ray’s head creates a 90 degrees with the ray
        Tube tb1 = new Tube(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 1d);
        assertEquals(new Vector(1, 0, 0), tb1.getNormal(new Point(2, 0, 0)),
                "Bad normal to Tube when point on the same level as p0");
    }
}