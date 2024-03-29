package renderer;

import scene.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class of Ray tracer
 */
public abstract class RayTracerBase {

    protected final Scene scene;

    /**
     * Constructor of the class
     *
     * @param sce scene params
     */
    public RayTracerBase(Scene sce) {
        scene = sce;
    }

    /**
     * Abstract function that helps us to create a color from a ray
     *
     * @param ray ray params
     * @return a color from the ray
     */
    public abstract Color traceRay(Ray ray, int j, int i);

    public abstract Color averageColor(List<Ray> rays, int j, int i) ;
}
