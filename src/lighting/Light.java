package lighting;
import primitives.*;

public abstract class Light {

    private Color intensity;
    protected Light(Color color)
    {
        this.intensity = color;
    }

    public Color getIntensity(){ return intensity;}
    public Color getIntensity(Color color){ return this.intensity;}
}
