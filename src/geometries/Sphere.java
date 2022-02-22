package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{
    private final Point center;
    private final double radius;

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    Sphere(Point center, double radius){
        this.center=center;
        this.radius=radius;
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
