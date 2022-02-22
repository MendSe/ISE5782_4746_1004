package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private final Point q0;
    private final Vector normal;

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    public Plane(Point d1, Point d2, Point d3) {
        this.q0= d3;
        Vector vec1 = d1.subtract(d2);
        Vector vec2 = d1.subtract(d3);
        Vector norm = vec1.crossProduct(vec2);
        this.normal = null;
    }
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
