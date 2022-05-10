package lighting;
import primitives.*;

public abstract class Light {

    private Color intensity;
    protected Light(Color color)
    {
        this.intensity = color;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return The intensity of the light source.
     */
    public Color getIntensity(){ return intensity;}

    /**
     * This function returns the intensity of the color.
     *
     * @param color The color of the light.
     * @return The intensity of the color.
     */
    public Color getIntensity(Color color){ return this.intensity;}
}
