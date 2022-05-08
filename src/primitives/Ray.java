package primitives;

import java.util.List;

import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;

/**
 * This class helps us to simulates a radius/ray
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * getter for Point p0
     *
     * @return point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for Vector dir
     *
     * @return vector dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Constructor of the ray class that initializes the fields p0 and dir
     *
     * @param p0 point parameter
     * @param v  vector parameters that used as a radius
     */
    public Ray(Point p0, Vector v) {
        this.p0 = p0;
        this.dir = v.normalize();
    }

    /**
     * Function that creates a point from a ray multiplicated by a value t
     *
     * @param t to multiply the vector of the ray
     * @return point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : this.p0.add(this.dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point findClosestPoint(List<Point> lp) {
        if(lp == null)
            return null;
        double mindist = 100000000;
        Point help = null;
        for (Point p : lp) {
            if (mindist > p.distance(p0)) {
                mindist = p.distance(p0);
                help = p;
            }
        }
        return help;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints)
    {
        if(geoPoints == null) return null;

        GeoPoint closest =  null;
        double closestDistSqu = Double.MAX_VALUE;

        for(GeoPoint geo : geoPoints) {
            double distanceSqu = geo.point.distanceSquared(p0);
            if(distanceSqu<closestDistSqu){
                closestDistSqu = distanceSqu;
                closest = geo;
            }
        }
        return closest;
    }

}
