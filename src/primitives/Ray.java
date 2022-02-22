package primitives;


public class Ray {
    private Point p0;
    private Vector dir;

    public Ray(Point p0,Vector v) {
        this.p0 = p0;
        this.dir = v.normalize();
    }
}
