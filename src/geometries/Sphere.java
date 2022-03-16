package geometries;

import primitives.*;

import java.util.List;

/**
 * Sphere class which creates a Sphere from a center point and a radius
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

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
    Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public List<Point> findIntsersections(Ray ray){
        return null;
    }
    @Override
    public Vector getNormal(Point point) {

        return (point.subtract(center)).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
