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
        Cylinder cy = new Cylinder(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)), 2d,1d);
        //TC01: Test of normal on the first disk
        assertEquals(new Vector(0, -1, 0), cy.getNormal(new Point(2, 0, 0)),
                "Bad normal to Cylinder when point is on the first disk");

        //TC02: Test of normal on the second disk
        assertEquals(new Vector(0, 1, 0), cy.getNormal(new Point(2, 1, 0)),
                "Bad normal to Cylinder when point is on the second disk");

        //TC03: Test of normal on the tube
        assertEquals(new Vector(0, 0,1 ), cy.getNormal(new Point(1, 0.5, 2)),
                "Bad normal to Cylinder when point is on the tube of the cylinder");

        // =============== Boundary Values Tests ==================
        //TC04: Test of normal in the center of first disk
        assertEquals(new Vector(0,- 1,0 ), cy.getNormal(new Point(1, 0, 0)),
                "Bad normal to Cylinder when point is between tube and first disk");

        //TC05: Test of normal in the center of second disk
        assertEquals(new Vector(0, 1,0 ), cy.getNormal(new Point(1, 1, 0)),
                "Bad normal to Cylinder when point is between tube and first disk");
    }
}