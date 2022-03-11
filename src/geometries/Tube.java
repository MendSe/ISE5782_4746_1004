package geometries;

import primitives.*;

/**
 * Tube class which create an infinite tube from a ray and a radius
 */
public class Tube implements Geometry {

    protected final Ray axisRay;
    protected final double radius;

    /**
     * getter for axisRay of the Tube
     *
     * @return The axisRay of the tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * getter for radius of the Tube
     *
     * @return the radius of the tube
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Tube constructor
     *
     * @param axisRay Object to initialize the axisRay field
     * @param radius value of the radius of the tube
     */
    Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {

        return null;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
