package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // normal of triangle
        Triangle tg = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3),tg.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }
}