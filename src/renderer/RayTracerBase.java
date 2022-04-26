package renderer;
import scene.*;
import primitives.*;

/**
 * Abstract class of Ray tracer
 */
public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * Constructor of the class
     * @param sce scene params
     */
    public RayTracerBase(Scene sce)
    {
        scene = sce;
    }

    /**
     * Abstract function that helps us to create a color from a ray
     * @param ray ray params
     * @return a color from the ray
     */
    public abstract Color traceRay(Ray ray);
}
