package primitives;

import static primitives.Util.*;

/**
 * This class helps us to simulates a radius/ray
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * getter for Point p0
     *
     * @return point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for Vector dir
     *
     * @return vector dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Constructor of the ray class that initializes the fields p0 and dir
     *
     * @param p0 point parameter
     * @param v  vector parameters that used as a radius
     */
    public Ray(Point p0, Vector v) {
        this.p0 = p0;
        this.dir = v.normalize();
    }

    /**
     * Function that creates a point from a ray multiplicated by a value t
     *
     * @param t to multiply the vector of the ray
     * @return point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : this.p0.add(this.dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
