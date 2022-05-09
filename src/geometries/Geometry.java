package geometries;

import primitives.*;

/**
 * Interface for the different geometry forms that implements function getNormal
 */
public abstract class Geometry extends Intersectable{

    protected Color emission = Color.BLACK;

    /**
     * Function of that calculates the normal of the used object at a point on the object
     *
     * @param p point on the object
     * @return the normal
     */
    public abstract Vector getNormal(Point p);

    /**
     * Returns the color of the light emitted by this object.
     *
     * @return The color of the light emitted by the object.
     */
    public Color getEmission()
    {
        return emission;
    }

    public Geometry setEmission(Color Emission)
    {
        emission = Emission;
        return this;
    }



}
