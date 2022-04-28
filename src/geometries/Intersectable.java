package geometries;

import primitives.*;

import java.util.List;

/**
 * Interface that implements the function findIntersection for intersections between rays and geometric objects
 */
public interface Intersectable {

    /**
     * function to calculate the intersection points between a ray and the geometric object
     * @param ray the ray which intersect the object
     * @return list of intersection points
     */
    public List<Point> findIntersections(Ray ray);
}
