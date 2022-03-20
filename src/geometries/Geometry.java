package geometries;

import primitives.*;

/**
 * Interface for the different geometry forms that implements function getNormal
 */
public interface Geometry extends Intersectable{
    /**
     * Function of that cqlculqtes the normal of the used object at a point on the object
     *
     * @param p point on the object
     * @return the normal
     */
    public Vector getNormal(Point p);
}
