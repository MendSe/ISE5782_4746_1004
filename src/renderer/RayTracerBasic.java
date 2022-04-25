package renderer;

import scene.Scene;
import primitives.*;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{

    public RayTracerBasic(Scene sce)
    {
        super(sce);
    }

    @Override
    public Color traceRay(Ray ray)
    {
        List<Point> inters = scene.geometries.findIntsersections(ray);
        if(inters !=null) {
            Point closestPoint = ray.findClosestPoint(inters);
            return calcColor(closestPoint);
        }
        return scene.background;
    }

    private Color calcColor(Point p)
    {
        return scene.ambientLight.getIntensity();
    }
}

