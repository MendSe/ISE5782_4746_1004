package renderer;

import scene.Scene;
import primitives.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Thus class inherits from the RayTracerBase class and helps us to create a color from a base point
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * Constructor of the class that calls the constructor of the father
     * @param sce scene params
     */
    public RayTracerBasic(Scene sce)
    {
        super(sce);
    }

    @Override
    public Color traceRay(Ray ray)
    {
        List<GeoPoint> inters = scene.geometries.findGeoIntersections(ray);
        return inters == null ? scene.background : calcColor(ray.findClosestGeoPoint(inters));
    }

    /**
     * This function returns a color from a given point
     * @param p point
     * @return the color of the ambient light of the scene
     */
    private Color calcColor(GeoPoint p)
    {
        return scene.ambientLight.getIntensity();
    }
}

