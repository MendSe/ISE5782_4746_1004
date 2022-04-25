package primitives;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(-1, 1, 2));

        //============Equivalence Partition==============
        //TC01 : Point in the middle of the list
        List<Point> p1 = new ArrayList<Point>();
        p1.add(new Point(2,0,-1));
        p1.add(new Point(0,0,1));
        p1.add(new Point(0,0,3));

        assertEquals(p1.get(1),ray.findClosestPoint(p1),"The middle of the list isn't the closest point");

        //============= Boundary Test Value===============
        //TC02 : null list
        assertNull(ray.findClosestPoint(null),"Didn't return null");

        //TC03: The closest point is the first of the list
        List<Point> p2 = new ArrayList<>();
        p2.add(new Point(0,0,1));
        p2.add(new Point(0,0,3));

        assertEquals(p2.get(0),ray.findClosestPoint(p2),"The first point isn't the closest");

        //TC04 : The closest point is the last of the list
        List<Point> p3 = new ArrayList<Point>();
        p3.add(new Point(2,0,-1));
        p3.add(new Point(0,0,3));
        p3.add(new Point(0,0,1));

        assertEquals(p3.get(2),ray.findClosestPoint(p3),"The last point isn't the closest");

    }
}