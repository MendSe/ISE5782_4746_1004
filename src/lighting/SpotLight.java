package lighting;

import com.sun.jdi.connect.Transport;
import primitives.*;

/**
 * Spotlight at a position.
 */
public class SpotLight extends PointLight {
    private Vector direction;
    private int narrowBeam = 1;


    /**
     * Constructs a spotlight with intensity, position and direction.
     *
     * @param intensity the intensity of the created spotlight
     * @param position  the position of the created spotlight
     * @param direction the direction of the spotlight
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Sets the narrow beam of the spotlight
     *
     * @param narrowBeam The angle of the narrow beam in degrees.
     * @return The SpotLight object.
     */
    public SpotLight setNarrowBeam(int narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }


    @Override
    public Color getIntensity(Point p) {
        double dl = Util.alignZero(getL(p).dotProduct(this.direction));
        if (dl <= 0) return Color.BLACK;
        if(this.narrowBeam != 1)  dl = Math.pow(dl, this.narrowBeam);
        return super.getIntensity(p).scale(dl);
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
