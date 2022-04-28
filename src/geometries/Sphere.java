package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Sphere class which creates a Sphere from a center point and a radius
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;
    private final double radius2; // square radius

    /**
     * getter for the center point of the sphere
     *
     * @return The center of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter for radius of the sphere
     *
     * @return The radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sphere constructor
     *
     * @param center Point object that initializes the center of the sphere
     * @param radius value of the radius of our sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radius2 = radius * radius;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector u;
        try { //when P0 and the center are the same point
            u = this.center.subtract(ray.getP0());
        } catch (IllegalArgumentException ex) {
            return List.of(ray.getPoint(this.radius));
        }

        double tm = u.dotProduct(ray.getDir());
        double d2 = u.lengthSquared() - tm * tm;
        double th2 = radius2 - d2;
        if (alignZero(th2) <= 0) return null;

        double th = Math.sqrt(th2);
        double t2 = alignZero(tm + th);// double t2 = alignZero(tm + th2);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm -th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
