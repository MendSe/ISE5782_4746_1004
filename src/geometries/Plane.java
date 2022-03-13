package geometries;

import primitives.*;
/**
 * Plane class represents a plane created with 3 points or a point and a vector
 */
public class Plane implements Geometry {
    private final Point q0;
    private final Vector normal;

    /**
     * getter for Point q0
     *
     * @return point q0
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter for normal vector
     *
     * @return the normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Plane constructor with 3 points
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p3;
        Vector vec1 = p1.subtract(p2);
        Vector vec2 = p1.subtract(p3);
        this.normal = (vec1.crossProduct(vec2)).normalize();
    }

    /**
     * Plane constructor with a point and a vector
     *
     * @param point
     * @param vec
     */
    public Plane(Point point, Vector vec) {
        this.q0 = point;
        this.normal = vec.normalize();
    }

    @Override
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
