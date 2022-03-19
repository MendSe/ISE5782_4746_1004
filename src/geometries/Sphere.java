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
    public List<Point> findIntsersections(Ray ray) {
        Vector u;
        try { //when P0 and the center are the same point
            u = this.center.subtract(ray.getP0());
        } catch (IllegalArgumentException ex) {
            return List.of(ray.getP0().add(ray.getDir().normalize().scale(this.radius)));
        }
        double dotP = u.dotProduct(ray.getDir().normalize());
        if ((dotP <= 0 && u.length() >= this.radius)) return null; //no intersections
        Point t = ray.getPoint(dotP);//Point t perpendicular to the center on the ray

        double x = 0;
        if (t.equals(this.center)) //when ray goes through center
        {
            x = this.radius;
        } else {
            Vector tToCent = this.center.subtract(t);
            if (dotP > 0 && tToCent.length() > this.radius) return null; // no intersections
            x = Math.sqrt((radius * radius) - tToCent.lengthSquared());
        }

        Point p1;
        if (alignZero(x) == 0) return null;//if tangent count return List.of(t);
        else p1 = t.add(ray.getDir().normalize().scale(x));

        if (isZero(u.length() - this.radius)) { // P0 on the sphere
            return List.of(p1);
        } else if (u.length() < this.radius) {  //P0 in the sphere
            return List.of(p1);
        } else {                             //P0 out of sphere
            Point p2 = t.add(ray.getDir().normalize().scale(-x));
            return List.of(p1, p2);
        }
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
