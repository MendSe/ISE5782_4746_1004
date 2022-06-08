package geometries;

import primitives.*;

/**
 * Abstract class for the different geometry forms that implements function getNormal
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();
    protected BoundingBox box;

    /**
     * Function of that calculates the normal of the used object at a point on the object
     *
     * @param p point on the object
     * @return the normal
     */
    public abstract Vector getNormal(Point p);

    /**
     * Returns the color of the light emitted by this object.
     *
     * @return The color of the light emitted by the object.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * This function sets the emission of the geometry to the given color.
     *
     * @param Emission The color of the light emitted by the object.
     * @return The Geometry object itself.
     */
    public Geometry setEmission(Color Emission) {
        emission = Emission;
        return this;

    }

    /**
     * This function helps us to get the material of the geometry
     *
     * @return the material used in the geometry
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * This function sets the material of the geometry and returns the geometry.
     *
     * @param material The material to use for the geometry.
     * @return The Geometry object itself.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    protected BoundingBox calculateBoundingBox(){return null;}

    @Override
    public BoundingBox getBoundingBox() {
        if (box != null) {
            return box;
        }
        box = calculateBoundingBox();
        return box;
    }
}
