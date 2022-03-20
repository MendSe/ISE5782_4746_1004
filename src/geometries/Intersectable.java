package geometries;

import primitives.*;

import java.util.List;

/**
 * Interface that implements the function findIntersection for intersections between rays and geometric objects
 */
public interface Intersectable {

    /**
     * function to calculate the coordinates of intersection points between a ray and an geometric object
     * @param ray the ray which intersect the object
     * @return list of intersection points between the ray and the object
     */
    public List<Point> findIntsersections(Ray ray);
}
