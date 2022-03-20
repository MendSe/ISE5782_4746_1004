package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Class test for Point
 */
class PointTest {

    Vector v1 = new Vector(1, 2, 3);
    Point p1 =new Point(1,2,3);
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(1,2,3),new Point(2,3,3).subtract(new Point(1,1,0)),"PointSubstract wrong result");

        // =============== Boundary Values Tests ==================
        //Vector 0
        assertThrows(IllegalArgumentException.class, () -> new Point(1,2,3).subtract(new Point(1,2,3)),
                "substract 2 same points does not throw exception");
    }

    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Point(2,4,5),new Point(1,2,2).add(new Vector(1,2,3)),"PointAdd wrong result");

        // =============== Boundary Values Tests ==================
        //Vector 0
        assertThrows(IllegalArgumentException.class, () -> new Point(1,2,3).add(new Vector(0,0,0)),
                "add vector 0 on a point does not throw exception");

    }

    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //Simple test
        assertEquals(5d,new Point(1,2,3).distanceSquared(new Point(1,1,1)),"DistanceSquared wrong result");

    }

    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        //Simple test
        assertEquals(3d,new Point(1,3,4).distance(new Point(0,1,2)),"Distance wrong result");
    }
}