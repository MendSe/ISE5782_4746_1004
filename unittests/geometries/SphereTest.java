package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test of normal on the Sphere
        Sphere sp = new Sphere(new Point(1, 2, 3),2d);
        assertEquals(new Vector(0, 1, 0), sp.getNormal(new Point(1, 4, 3)), "Bad normal to Sphere");
    }
}