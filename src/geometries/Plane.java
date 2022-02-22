package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Plane class represents a plane created with 3 points or a point and a vector
 */
public class Plane implements Geometry{
    private final Point q0;
    private final Vector normal;
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
        this.q0= d3;
        Vector vec1 = d1.subtract(d2);
        Vector vec2 = d1.subtract(d3);
        Vector norm = vec1.crossProduct(vec2);
        this.normal = null;
    }
    /**
     * Plane constructor with a point and a vector
     */
    public  Plane(Vector vec,Point point){
        this.q0 =point;
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
