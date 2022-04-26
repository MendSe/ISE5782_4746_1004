package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class responsable of the intensity of the light
 */
public class AmbientLight {

    final private Color intensity;

    /**
     * constructor
     * @param iA Color
     * @param kA Double3
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
