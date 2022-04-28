package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

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
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        var intersections = plane.findIntersections(ray);
        if (intersections == null)
            return null;

        Point p0 = ray.getP0();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        Vector vd = ray.getDir();
        double num1 = alignZero(vd.dotProduct(n1));
        if (num1 == 0) return null;
        double num2 = alignZero(vd.dotProduct(n2));
        if (num1 * num2 <= 0) return null;
        double num3 = alignZero(vd.dotProduct(n3));
        if (num1 * num3 <= 0) return null;

        return intersections;
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString();
    }
}
