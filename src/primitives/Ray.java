package primitives;

/**
 * This class helps us to simulates a radius/ray
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor of the ray class that initializes the fields p0 and dir
     *
     * @param p0 point parameter
     * @param v vector parameters that used as a radius
     */
    public Ray(Point p0, Vector v) {
        this.p0 = p0;
        this.dir = v.normalize();
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
