package lighting;
import primitives.*;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC;
    private double kL;
    private double kQ;

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
