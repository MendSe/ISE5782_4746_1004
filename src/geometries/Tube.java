package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Tube implements Geometry{

    protected final Ray  axisRay;
    protected final double radius;

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

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
