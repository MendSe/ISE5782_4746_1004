package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
/**
 * Tube class which create an infinite tube from a ray and a radius
 */
public class Tube implements Geometry{

    protected final Ray  axisRay;
    protected final double radius;
    /**
     * getter for axisRay of the Tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }
    /**
     * getter for radius of the Tube
     */
    public double getRadius() {
        return radius;
    }
    /**
     * Tube constructor
     */
    Tube(Ray axisRay, double radius){
        this.axisRay=axisRay;
        this.radius=radius;
    }
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
