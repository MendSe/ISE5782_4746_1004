package lighting;

import primitives.*;

/**
 * Light with a position.
 */
public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1d; //constant attenuation factor
    private double kL = 0d; //linear attenuation factor
    private double kQ = 0d; //quadratic attenuation factor

    /**
     * Constructs a point light with an intensity and a position
     *
     * @param intensity intensity of the point light
     * @param position  position of the point light
     */
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
        double dSqr = p.distanceSquared(this.position);
        return intensity.reduce(this.kC + this.kL * Math.sqrt(dSqr) + this.kQ * dSqr);
    }

    /**
     * This function helps us to set the new value of the kC field
     *
     * @param c new value of kC
     * @return the current point light
     */
    public PointLight setKc(double c) {
        this.kC = c;
        return this;
    }

    /**
     * This function helps us to set the new value of the kL field
     *
     * @param L new value of kL
     * @return the current point light
     */
    public PointLight setKl(double L) {
        this.kL = L;
        return this;
    }

    /**
     * This function helps us to set the new value of the kQ field
     *
     * @param Q new value of kQ
     * @return the current point light
     */
    public PointLight setKq(double Q) {
        this.kQ = Q;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }

}
