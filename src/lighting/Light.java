package lighting;

import primitives.*;

/**
 * abstract class Light which defines the intensity of the light
 */
public abstract class Light {
    /**
     * intensity of the light
     */
    protected final Color intensity;

    /**
     * light constructor
     * @param color intensity of the light
     */
    protected Light(Color color) {
        this.intensity = color;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return The intensity of the light source.
     */
    public Color getIntensity() {
        return intensity;
    }
}
