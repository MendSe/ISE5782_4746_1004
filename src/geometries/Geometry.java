package geometries;

import primitives.*;

public interface Geometry {
    /**
     * Function of interface Geometry that return the normal of the used object
     *
     * @param p
     * @return normal
     */
    public Vector getNormal(Point p);
}
