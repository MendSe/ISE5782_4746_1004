package renderer;

import scene.Scene;
import primitives.*;

import java.util.List;

/**
 * Thus class inherits from the RayTracerBase class and helps us to create a color from a base point
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * Constructor of the class that calls the ctor of the father
     * @param sce scene params
     */
    public RayTracerBasic(Scene sce)
    {
        super(sce);
    }

    @Override
    public Color traceRay(Ray ray)
    {
        List<Point> inters = scene.geometries.findIntersections(ray);
        if(inters !=null) {
            Point closestPoint = ray.findClosestPoint(inters);
            return calcColor(closestPoint);
        }
        return scene.background;
    }

    /**
     * This function returns a color from a given point
     * @param p point
     * @return the color of the ambient light of the scene
     */
    private Color calcColor(Point p)
    {
        return scene.ambientLight.getIntensity();
    }
}

