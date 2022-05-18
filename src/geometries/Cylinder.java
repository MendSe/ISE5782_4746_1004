package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Cylinder class represents a 3D Tube from it inherited Tube class with a maximum height
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Cylinder constructor
     *
     * @param axisRay axisRay value
     * @param radius  radius value
     * @param height  height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = this.axisRay.getP0();
        Vector dir = this.axisRay.getDir();
        Vector p0ToPoint;
        try {                                //point = P0
            p0ToPoint = point.subtract(p0);
        } catch (IllegalArgumentException ex) {
            return dir.normalize().scale(-1);
        }

        double scale = (dir.dotProduct(p0ToPoint));

        if (isZero(scale)) {
            return dir.normalize().scale(-1);
        } else if (scale == this.height) {
            return dir.normalize();
        } else {
            Point p1 = this.axisRay.getPoint(scale);//p0.add(dir.scale(scale));
            return (point.subtract(p1)).normalize();
        }
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Plane up = new Plane(axisRay.getP0(), axisRay.getDir());
        Plane down = new Plane(axisRay.getP0().add(axisRay.getDir().normalize().scale(height)), axisRay.getDir());
        GeoPoint geoPoint1 = baseIntersection(up, ray, up.getQ0());
        GeoPoint geoPoint2 = baseIntersection(down, ray, down.getQ0());
        if (geoPoint1 != null && geoPoint2 != null) return List.of(geoPoint1, geoPoint2);
        geoPoint1 = geoPoint1 != null ? geoPoint1 : geoPoint2;

        List<GeoPoint> list = super.findGeoIntersectionsHelper(ray, maxDistance);
        List<GeoPoint> list2 = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if ((alignZero(axisRay.getDir().dotProduct(list.get(i).point.subtract(down.getQ0()))) < 0
                        && alignZero(axisRay.getDir().dotProduct(list.get(i).point.subtract(up.getQ0()))) > 0))
                    list2.add(list.get(i));
            }
        }
        if (list2.isEmpty()) return null;
        return geoPoint1 != null ? List.of(geoPoint1, list2.get(0)) : list2;

    }

    /**
     * Help function for findintersection to find intersections with the two cylinders bases
     *
     * @param plane  cylinder base
     * @param ray    cylinder ray
     * @param center cylinder center of base
     * @return
     */
    private GeoPoint baseIntersection(Plane plane, Ray ray, Point center) {
        List<GeoPoint> lst = plane.findGeoIntersections(ray); //intersection Points with Plane
        if (lst == null) return null;
        Point p = lst.get(0).point;
        double radius2 = this.radius * this.radius;
        return alignZero(p.distanceSquared(center) - radius2) < 0 ? new GeoPoint(this, p) : null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                super.toString() + "}";
    }
}
