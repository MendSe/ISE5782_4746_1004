package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private Point q0;
    private Vector normal;
    /**
    * getter for Point q0
    */
    public Point getQ0() {
        return q0;
    }
    /**
    * getter for normal vector
    */
    public Vector getNormal() {
        return normal;
    }
/**
 * Plane constructor with 3 points
 */
    public Plane(Point d1, Point d2, Point d3) {
        Vector vec1 = d1.subtract(d2);
        Vector vec2 = d1.subtract(d3);
        Vector norm = vec1.crossProduct(vec2);
        this.normal = null;
    }
    public  Plane(Vector vec){
        this.normal=null;
    }
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
