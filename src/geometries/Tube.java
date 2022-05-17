package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Tube class which create an infinite tube from a ray and a radius
 */
public class Tube extends Geometry {

    protected final Ray axisRay;
    protected final double radius;

    /**
     * getter for axisRay of the Tube
     *
     * @return The axisRay of the tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * getter for radius of the Tube
     *
     * @return the radius of the tube
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Tube constructor
     *
     * @param axisRay Object to initialize the axisRay field
     * @param radius  value of the radius of the tube
     */
    Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point pa = this.axisRay.getP0();
        Vector va = this.axisRay.getDir();
        double radius2 = this.radius * this.radius;
        double a, b, c; //ax^2 + bx + c

        Vector vecA = v;
        try {
            double vva = v.dotProduct(va); //(v,va)
            if (!isZero(vva)) vecA = v.subtract(va.scale(vva)); //v-(v,va)va
            a = vecA.lengthSquared(); //(v-(v,va)va)^2
        } catch (IllegalArgumentException e) {
            return null; //Ray is parallel to axisRay
        }

        //calculates deltaP (delP), b, c:
        try {
            Vector deltaP = p0.subtract(pa); //p0-pa
            Vector deltaPMinusDeltaPVaVa = deltaP;
            double deltaPVa = deltaP.dotProduct(va); //(delP,va)va)
            if (!isZero(deltaPVa)) deltaPMinusDeltaPVaVa = deltaP.subtract(va.scale(deltaPVa));
            b = 2 * (vecA.dotProduct(deltaPMinusDeltaPVaVa)); //2(v-(v,va)va,delP-(delP,va)va)
            c = deltaPMinusDeltaPVaVa.lengthSquared() - radius2; //(delP-(delP,va)va)^2 - r^2
        } catch (IllegalArgumentException e) {
            b = 0; //if delP = 0, or (delP-(delP,va)va = (0, 0, 0)
            c = -1 * radius2;
        }

        //solving equation: ax^2 + bx + c = 0
        double discriminator = alignZero(b * b - 4 * a * c); //discriminator: b^2 - 4ac
        if (discriminator <= 0) return null; //there are no intersections because Ray is parall

        //the solutions for the equation: (-b +- discriminator) / 2a
        double sqrtDiscriminator = Math.sqrt(discriminator);
        double x1 = alignZero(-b + sqrtDiscriminator) / (2 * a);
        double x2 = alignZero(-b - sqrtDiscriminator) / (2 * a);

        //if x1 or x2 are bigger than maxDistance they wll be set to negative value
        if (alignZero(x1 - maxDistance) > 0) x1 = -1;
        if (alignZero(x2 - maxDistance) > 0) x2 = -1;

        if (x1 > 0 && x2 > 0)
            return List.of( new GeoPoint(this, ray.getPoint(x1)), new GeoPoint(this, ray.getPoint(x2)));
        if (x1 > 0) return List.of(new GeoPoint(this, ray.getPoint(x1)));
        if (x2 > 0) return List.of(new GeoPoint(this, ray.getPoint(x2)));

        return null;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = this.axisRay.getP0();
        Vector dir = this.axisRay.getDir();

        Vector p0ToPoint = point.subtract(p0);
        double scale = (dir.dotProduct(p0ToPoint));

        if (isZero(scale))
            return p0ToPoint.normalize();

        Point p1 = this.axisRay.getPoint(scale);//p0.add(dir.scale(scale));
        return (point.subtract(p1)).normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
