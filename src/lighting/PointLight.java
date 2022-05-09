package lighting;
import primitives.*;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC = 1d;
    private double kL = 0d;
    private double kQ = 0d;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(this.position);
        return this.getIntensity().reduce(this.kC + this.kL * d + this.kQ * d * d);
    }

    public PointLight setkC(double c) {
        kC = c;
        return this;
    }

    public PointLight setkL(double L) {
        kL = L;
        return this;
    }

    public PointLight setkQ(double Q) {
        kQ = Q;
        return this;
    }
}
