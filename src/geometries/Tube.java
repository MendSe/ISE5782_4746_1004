package geometries;

import primitives.*;
import static primitives.Util.*;

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

       Point p0 =this.axisRay.getP0();
       Vector dir=this.axisRay.getDir();
       Vector p0ToPoint=point.subtract(p0);
       double scale=alignZero(dir.dotProduct(p0ToPoint));
       if(isZero(scale))return p0ToPoint.normalize();
       Point p1 =p0.add(dir.scale(scale));
       return (point.subtract(p1)).normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
