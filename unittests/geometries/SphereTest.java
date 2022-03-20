package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class test for Sphere
 */
class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //Test of normal on the Sphere
        Sphere sp = new Sphere(new Point(1, 2, 3),2d);
        assertEquals(new Vector(0, 1, 0), sp.getNormal(new Point(1, 4, 3)), "Bad normal to Sphere");
    }

    @Test
    void findIntsersections(){
        Sphere sp = new Sphere(new Point(2, 0, 0),2d);
        // ============ Equivalence Partitions Tests ==============
        //EP01: Ray intersect sphere in 2 points
        Point p1=new Point(0.267949192431122,-1,0);
        Point p2=new Point(3.732050807568877,-1,0);
        List<Point> result=sp.findIntsersections(new Ray(new Point(0,-1,0),new Vector(4,0,0)));
        assertEquals(2,result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Bad intersections when ray intersects the Sphere 2 times");

        //EP02: Ray in Sphere
        p1=new Point(3.732050807568877,-1,0);
        result=sp.findIntsersections(new Ray(new Point(1,-1,0),new Vector(3,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(List.of(p1), result, "Bad intersections when ray is in the sphere and intersects the Sphere");

        //EP03: Ray, only tail intersect
        assertNull(sp.findIntsersections(new Ray(new Point(4,-1,0),new Vector(3,0,0))), "Bad intersections when ray doesn't intersect the sphere but his tail would intersect it ");

        //EP04: Ray and tail no intersections
        assertNull(sp.findIntsersections(new Ray(new Point(0,-3,0),new Vector(3,0,0))), "Bad intersections when nor ray nor his tail intersects the sphere ");


        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        //BVA01: P0 on the Sphere and Ray out of it
        assertNull(sp.findIntsersections(new Ray(new Point(0,0,0),new Vector(-1,-1,0))), "Bad intersections when P0 on the Sphere and ray out of it ");

        //BVA02: P0 on the Sphere and Ray intersect the sphere in
        result=sp.findIntsersections(new Ray(new Point(0.267949192431122,-1,0),new Vector(4,0,0)));
        p1=new Point(3.732050807568877,-1,0);
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(result,List.of(p1), "Bad intersections when P0 is on the sphere and Ray intersects the Sphere ");

        // **** Group: Ray's line is tangent to the sphere
        //BVA03: Ray tangent to sphere

        assertNull(sp.findIntsersections(new Ray(new Point(0,-2,0),new Vector(4,0,0))), "Bad intersection when the Ray is tangent to the sphere");

        //BVA04: P0 on Sphere Ray tangent to sphere
        assertNull(sp.findIntsersections(new Ray(new Point(2,-2,0),new Vector(4,0,0))), "Bad intersection when P0 on the sphere and the Ray is tangent to it");

        //BVA05: tail tangent to the sphere
        assertNull(sp.findIntsersections(new Ray(new Point(3,-2,0),new Vector(4,0,0))), "Bad intersection when tail of the Ray is tangent to the sphere");

        // **** Group: Ray's line goes through the center
        //BVA06: P0 on the sphere and the tail intersect with the center
        assertNull(sp.findIntsersections(new Ray(new Point(0,0,0),new Vector(-4,0,0))), "Bad intersection when P0 is on the sphere and the tail intersect the center");

        //BVA07: P0 and the center are the same point
        result=sp.findIntsersections(new Ray(new Point(2,0,0),new Vector(4,0,0)));
        p1=new Point(4,0,0);
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(result,List.of(p1),"Bad intersection when P0 and the center are the same point");

        //BVA08: Ray starts after the sphere and his tail intersect the center
        assertNull(sp.findIntsersections(new Ray(new Point(5,0,0),new Vector(1,0,0))), "Bad intersection when the ray starts after the sphere and his tail intersect the center");

        //BVA09: P0 on the sphere and ray goes through the center
        result=sp.findIntsersections(new Ray(new Point(0,0,0),new Vector(5,0,0)));
        p1=new Point(4,0,0);
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(result,List.of(p1),"Bad intersection when P0 is on the sphere and the ray goes through the center");

        //BVA10: Ray start in the sphere and his tail intersect the center
        result=sp.findIntsersections(new Ray(new Point(3,0,0),new Vector(4,0,0)));
        p1=new Point(4,0,0);
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(result,List.of(p1),"Bad intersection when P0 is on the sphere and the ray goes through the center");

        //BVA11: Ray start before the sphere and intersect it passing through the center
        p1=new Point(0,0,0);
        p2=new Point(4,0,0);
        result=sp.findIntsersections(new Ray(new Point(-1,0,0),new Vector(6,0,0)));
        assertEquals(2,result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Bad intersections when ray intersects the Sphere 2 times by going through his center");

        // **** Group: Special cases
        //BVA12: Ray outside the sphere and perpendicular to it center
        assertNull(sp.findIntsersections(new Ray(new Point(2,-5,0),new Vector(1,0,0))), "Bad intersection when the ray starts outside the sphere and perpendicular to his center");
    }
}