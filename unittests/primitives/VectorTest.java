package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/**
 * Class test for Vector
 */
class VectorTest {

    Vector v1 = new Vector(1,2,3);
    Vector v2 = new Vector(0, 3, -2);
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test
        assertEquals(v1, new Vector(1,1,1).add(new Vector(0,1,2)),"Wrong vector Addition");
        // =============== Boundary Values Tests ==================
        //TC02: Vector 0
        assertThrows(IllegalArgumentException.class,() ->new Vector(1,2,3).add(new Vector(-1,-2,-3)),"Vector 0 doesn't exist here");
    }

    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple Test
        assertEquals(new Vector(1,-1,5),v1.subtract(v2),"Wrong vector subtraction");
    }

    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test
        assertEquals(new Vector(2,4,6),v1.scale(2),"Wrong scaling");
        // =============== Boundary Values Tests ==================
        //TC02: Vector 0
        assertThrows(IllegalArgumentException.class,()->v1.scale(0),"We cannot scale by 0");
    }

    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple Test
        assertEquals(0d,v1.dotProduct(v2),0.01,"Wrong dot product");
    }

    @Test
    void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");


        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC03: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test
        assertEquals(14d,v1.lengthSquared(),"Wrong length squared");
    }

    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple Test
        assertEquals(5d,new Vector(0,3,4).length(),0.0001,"Wrong length computation");
    }

    @Test
    void normalize() {
        Vector normal= v1.normalize();
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple Test
        assertEquals(1d,v1.normalize().length(),0.00001,"Wrong normalize");
    }
}