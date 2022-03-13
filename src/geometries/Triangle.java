package geometries;

import primitives.*;

import java.util.List;

/**
 * Triangle class which inherits from Polygon class and creates a 2D triangle in a 3D space
 */
public class Triangle extends Polygon {
    /**
     * Triangle constructor
     *
     * @param p1 coordinate value
     * @param p2 coordinate value
     * @param p3 coordinate value
     */
    Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString();
    }
}
