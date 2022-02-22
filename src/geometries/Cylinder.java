package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Cylinder extends Tube{
    private final double height;

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
                 super.toString()+"}";
    }
}
