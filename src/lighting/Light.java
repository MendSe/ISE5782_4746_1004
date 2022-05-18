package lighting;

import primitives.*;

public abstract class Light {
    protected final Color intensity;

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
