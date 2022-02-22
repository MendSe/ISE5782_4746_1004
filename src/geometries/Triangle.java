package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.List;
/**
 * Triangle class which inherits from Polygon class and creates a 2D triangle in a 3D space
 */
public class Triangle extends Polygon{
    /**
     * Triangle constructor
     */
    Triangle (Point d1,Point d2,Point d3){
      super(d1,d2,d3);
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString();
    }
}
