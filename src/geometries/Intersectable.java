package geometries;

import primitives.*;

import java.util.List;

/**
 * Abstract class that contains the function findIntersection for intersections between rays and geometric objects as well as
 * the GeoPoint class
 */
public abstract class Intersectable {

    /**
     * A pair of point and its geometry
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor of the GeoPoint class
         *
         * @param geometry the geometry used
         * @param point    a point in the geometry used
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * function to calculate the intersection points between a ray and the geometric object
     *
     * @param ray the ray which intersect the object
     * @return list of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * > This function returns a list of all the points where the ray intersects the surface of the sphere
     *
     * @param ray The ray to intersect with the object
     * @return A list of GeoPoints.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * "Finds the intersections of a ray with the Earth's surface, up to a maximum distance."
     * <p>
     * The function is declared as `public final` and `static`. The `final` keyword means that the function cannot be
     * overridden by a subclass. The `static` keyword means that the function is a class method, not an instance method
     *
     * @param ray         The ray to intersect with the GeoJsonFeature.
     * @param maxDistance The maximum distance from the ray origin to the intersection point.
     * @return A list of GeoPoints.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Find the intersection points of the given ray with this GeoShape, up to the given maximum distance
     *
     * @param ray         The ray to intersect with the object.
     * @param maxDistance The maximum distance from the ray origin to the intersection point.
     * @return A list of GeoPoints.
     */
    protected abstract List<GeoPoint>
    findGeoIntersectionsHelper(Ray ray, double maxDistance);


}
