package renderer;

import geometries.Triangle;
import lighting.LightSource;
import scene.Scene;
import primitives.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class inherits from the RayTracerBase class and helps us to create a color from a base point
 */
public class RayTracerBasic extends RayTracerBase {

    private static final Double3 INITIAL_K = new Double3(1.0);
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * Constructor of the class that calls the constructor of the father
     *
     * @param sce scene params
     */
    public RayTracerBasic(Scene sce) {
        super(sce);
    }

    @Override
    public Color traceRay(Ray ray, int j, int i) {
        // List<GeoPoint> inters = scene.geometries.findGeoIntersections(ray);
        //return inters == null ? scene.background : calcColor(ray.findClosestGeoPoint(inters), ray);
        GeoPoint gp = findClosestIntersection(ray);
        return gp != null ? calcColor(gp, ray) : scene.getBackgroundColor(j, i);
    }

    /**
     * Calculates the color of the intersected object
     * on the intersection point with a given ray.
     * Starts the recursion call to calculate the reflection
     * and the refraction with starting level of
     * {@code MAX_CALC_COLOR_LEVEL} and starting k
     * of {@code INITIAL_K}.
     *
     * @param gp  the intersection point with geometry
     * @param ray the ray that caused the intersection
     * @return the color on the intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color of the intersected object
     * on the intersection point with a given ray.
     * This is recursive function that calculates also
     * the reflection and refraction.
     *
     * @param p  the intersection point with geometry
     * @param ray the ray that caused the intersection
     * @param MCCL the recursion level
     * @param iK the ratio og the current ray's color to the color of the previous ray
     * @return the color on the intersection point
     */
    private Color calcColor(GeoPoint p, Ray ray, int MCCL, Double3 iK) {
        Color color = p.geometry.getEmission().add(calcLocalEffects(p, ray, iK));
        return 1 == MCCL ? color : color.add(calcGlobalEffects(p, ray, MCCL, iK));
    }

    /**
     * It calculates the color of the point by calculating the color of the reflected and refracted rays
     *
     * @param gp The point on the geometry that the ray intersected with.
     * @param ray the ray that hit the geometry
     * @param level the recursion level.
     * @param k the color of the light that is reflected from the current point.
     * @return The color of the point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector normal = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kr = k.product(material.kR);
        Double3 kkr = k.product(kr);

        Ray reflectedRay = constructReflectedRay(normal, gp.point, ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(material.kR));
        }

        Double3 kkt = k.product(material.kT);
        Ray refractedRay = constructRefractedRay(normal, gp.point, ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(material.kT));
            }
        }
        return color;
    }

    /**
     * It calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param geoPoint The point on the geometry that the ray intersects with.
     * @param ray      the ray that intersects the geometry
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Color color = geoPoint.geometry.getEmission();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : this.scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Double3 ktr = transparency(geoPoint, lightSource, l, n);
                Double3 x = ktr.product(k);
                if (!x.lowerThan(MIN_CALC_COLOR_K)) {
                    Color intensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(calcDiffusive(material.kD, l, n, intensity),
                            calcSpecular(material.kS, l, n, v, material.nShininess, intensity));
                }
            }
        }
        return color;
    }

    /**
     * "Calculate the diffusive component of the light intensity at a point on a surface."
     *
     * @param kD        The diffuse coefficient of the material.
     * @param l         the vector from the point on the surface to the light source
     * @param n         the normal vector of the surface
     * @param intensity the color of the light source
     * @return The color of the diffuse reflection.
     */
    private Color calcDiffusive(Double3 kD, Vector l, Vector n, Color intensity) {
        return intensity.scale(kD.scale(Math.abs(l.dotProduct(n))));
    }

    /**
     * The specular component is the product of the intensity of the light source and the specular coefficient of the
     * material, raised to the power of the shininess of the material
     *
     * @param kS         The specular coefficient.
     * @param l          the vector from the point to the light source
     * @param n          normal vector
     * @param v          the vector from the point to the camera
     * @param nShininess The shininess of the material.
     * @param intensity  the color of the light source
     * @return The color of the point on the surface of the sphere.
     */
    private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess, Color intensity) {
        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
        double vr = alignZero(v.dotProduct(r));
        return vr >= 0 ? Color.BLACK : intensity.scale(kS.scale(Math.pow(-vr, nShininess)));
    }

    /**
     * If the ray from the point to the light source intersects any geometry, then the point is shaded, otherwise it's
     * unshaded
     *
     * @param light The light source
     * @param gp    The point on the geometry that we're shading.
     * @param l     The vector from the light source to the point on the geometry.
     * @param n     the normal vector to the surface at the intersection point
     * @param nv    the dot product of the normal vector and the vector from the light source to the point.
     * @return true if the point is unshaded, and false if it is shaded.
     */
    private boolean unshaded(LightSource light, GeoPoint gp, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
        if (intersections != null) {
            for (GeoPoint geo : intersections) {
                if (geo.geometry.getMaterial().kT == Double3.ZERO)
                    return false;
            }
        }

        return true;
    }

    /**
     *  Construct a reflected ray from a point, a normal vector, and an incoming ray
     *
     * @param n The normal vector of the surface at the point of intersection.
     * @param point The point of intersection
     * @param inRay The ray that hit the object
     * @return A ray that is reflected off the surface of the object.
     */
    private Ray constructReflectedRay(Vector n, Point point, Vector inRay) {
        return new Ray(point, inRay.subtract(n.scale(2 * inRay.dotProduct(n))), n);
    }

    /**
     * Construct a refracted ray from the intersection point, the incoming ray, and the normal vector.
     *
     * @param n the normal vector of the surface
     * @param point The point of intersection between the ray and the object.
     * @param inRay the ray that hit the object
     * @return A new ray with the same origin as the point of intersection, the same direction as the incoming ray, and the
     * same normal as the normal of the surface.
     */
    private Ray constructRefractedRay(Vector n, Point point, Vector inRay) {
        return new Ray(point, inRay, n);
    }


    /**
     * It finds the closest intersection point of a ray with the scene's geometries
     *
     * @param ray The ray that we want to find the closest intersection to.
     * @return The closest intersection point to the camera.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * The function calculates the transparency of the point, by calculating the transparency of each point on the way to
     * the light source
     *
     * @param geoPoint The point on the geometry that we are currently calculating the color for.
     * @param ls The light source
     * @param l the direction of the light source
     * @param n The normal vector of the point on the surface of the object.
     * @return The transparency of the point.
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = ls.getDistance(geoPoint.point);
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return new Double3(1d);

        Double3 ktr = new Double3(1d);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }

        return ktr;
    }
}

