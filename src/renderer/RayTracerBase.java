package renderer;
import scene.*;
import primitives.*;

public abstract class RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene sce)
    {
        scene = sce;
    }

    public abstract Color traceRay(Ray ray);
}
