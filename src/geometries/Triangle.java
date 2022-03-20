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
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector vd = ray.getDir();

        if (plane.findIntsersections(ray) == null)
            return null;

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double num1 = vd.dotProduct(n1);
        double num2 = vd.dotProduct(n2);
        double num3 = vd.dotProduct(n3);

        if ((num1 > 0 && num2 > 0 && num3 > 0) || (num1 < 0 && num2 < 0 && num3 < 0))
            return plane.findIntsersections(ray);

        return null;
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString();
    }
}
