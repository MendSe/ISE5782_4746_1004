package geometries;

import primitives.*;

import static primitives.Util.isZero;

/**
 * Cylinder class represents a 3D Tube from it inherited Tube class with a maximum height
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Cylinder constructor
     *
     * @param axisRay axisRay value
     * @param radius radius value
     * @param height height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = this.axisRay.getP0();
        Vector dir = this.axisRay.getDir();
        Vector p0ToPoint;
        try{                                //point = P0
            p0ToPoint = point.subtract(p0);
        }
        catch(IllegalArgumentException ex)
        {
            return dir.normalize().scale(-1);
        }

        double scale = (dir.dotProduct(p0ToPoint));

        if (isZero(scale)) {
            return dir.normalize().scale(-1);
        } else if(scale==this.height){
            return dir.normalize();
        }else{
            Point p1 = this.axisRay.getPoint(scale);//p0.add(dir.scale(scale));
            return (point.subtract(p1)).normalize();
        }
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                super.toString() + "}";
    }
}
