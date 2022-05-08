package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class responsible for the intensity of the light
 */
public class AmbientLight {
    final private Color intensity;

    /**
     * Constructs ambient light with initial intensity and attenuation factor
     *
     * @param iA initial intensity
     * @param kA attenuation factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Constructs ambient light for complete darkness
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * getter for intensity
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
