package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon{
    Triangle (Point d1,Point d2,Point d3){
      super(d1,d2,d3);
        //  super(new List<Point>(){{add(d1);add(d2);add(d3);}});

    }
}
