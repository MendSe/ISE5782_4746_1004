package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Cylinder class represents a 3D Tube from it inherited Tube class with a maximum height
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Cylinder constructor
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                super.toString() + "}";
    }
}
