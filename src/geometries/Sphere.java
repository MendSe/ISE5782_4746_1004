package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class which creates a Sphere from a center point and a radius
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

    /**
     * getter for the center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter for radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sphere constructor
     */
    Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
