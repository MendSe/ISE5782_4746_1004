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

    private static final double DELTA = 0.1;

    /**
     * Constructor of the class that calls the constructor of the father
     *
     * @param sce scene params
     */
    public RayTracerBasic(Scene sce) {
        super(sce);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> inters = scene.geometries.findGeoIntersections(ray);
        return inters == null ? scene.background : calcColor(ray.findClosestGeoPoint(inters), ray);
    }

    /**
     * This function returns a color from a given point
     *
     * @param p point
     * @return the color of the ambient light of the scene
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(p, ray));
    }

    /**
     * It calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param geoPoint The point on the geometry that the ray intersects with.
     * @param ray      the ray that intersects the geometry
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Color color = geoPoint.geometry.getEmission();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource,geoPoint, l, n, nv)) {
                    Color intensity = lightSource.getIntensity(geoPoint.point);
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

    private boolean unshaded(LightSource light, GeoPoint gp, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Ray lightRay = new Ray(gp.point.add(epsVector), lightDirection);

        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(lightRay,light.getDistance(gp.point));
        return intersections == null;
    }

}

