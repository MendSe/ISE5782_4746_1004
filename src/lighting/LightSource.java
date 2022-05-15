package lighting;
import primitives.*;

/**
 * Interface that implements the getIntensity and the getL functions
 */
public interface LightSource {

    /**
     * This function helps us to get the intensity of a light/color
     *
     * @param p a given point
     * @return the intensity of the light / color at this given point
     */
    public Color getIntensity(Point p);

    /**
     * The vector l which is the vector between the source and the position     *
     *
     * @param p the position of the light collision
     * @return the resulted vector
     */
    public Vector getL(Point p);

    double getDistance(Point point);

}
