package lighting;
import primitives.*;

/**
 * A directional light is a light source that has a direction but no position
 */
public class DirectionalLight extends Light implements LightSource {
    private final Vector direction;

    /**
     * The constructor of the class. It is called when we create a new object of the class.
     *
     * @param intensity The intensity of the light
     * @param direction The direction of the light
     */
    public DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p){
        return direction;
    }

}
