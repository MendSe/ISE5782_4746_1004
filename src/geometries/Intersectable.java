package geometries;

import primitives.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface that implements the function findIntersection for intersections between rays and geometric objects
 */
public abstract class Intersectable {

    /**
     * > A GeoPoint is a Geometry that is a Point
     */
    public static class GeoPoint{
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry,Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o)
        {
            if(this == o) return true;
            if(!(o instanceof GeoPoint)) return false;
            GeoPoint geop = (GeoPoint) o;
            return geometry.equals(geop.geometry) && point.equals(geop.point);
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
     * @param ray the ray which intersect the object
     * @return list of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    public List<GeoPoint> findGeoIntersections(Ray ray)
    {
        return findGeoIntersectionHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionHelper(Ray ray);
}
