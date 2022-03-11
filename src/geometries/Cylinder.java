package geometries;

import primitives.*;

/**
 * Cylinder class represents a 3D Tube from it inherited Tube class with a maximum height
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Cylinder constructor
     *
     * @param axisRay axisRay value
     * @param radius radius value
     * @param height height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
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
