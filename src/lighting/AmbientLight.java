package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class responsible for the intensity of the light
 */
public class AmbientLight extends Light {

    /**
     * Constructs ambient light with initial intensity and attenuation factor
     *
     * @param iA initial intensity
     * @param kA attenuation factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Constructs ambient light for complete darkness
     */
    public AmbientLight() {
        super(Color.BLACK);
    }


}
