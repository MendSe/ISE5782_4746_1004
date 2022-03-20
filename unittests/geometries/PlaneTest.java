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
}