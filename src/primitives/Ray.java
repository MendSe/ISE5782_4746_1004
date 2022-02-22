package primitives;


public class Ray {
    private final Point p0;
    private final Vector dir;

    public Ray(Point p0,Vector v) {
        this.p0 = p0;
        this.dir = v.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (p0 != null ? !p0.equals(ray.p0) : ray.p0 != null) return false;
        return dir != null ? dir.equals(ray.dir) : ray.dir == null;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
