package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class geometries to compile geometrical objects in a intersectableList
 */
public class Geometries implements Intersectable {

    private final List<Intersectable> intersectableList;

    /**
     * Default constructor
     */
    public Geometries(){
        this.intersectableList =new LinkedList<>();
    }

    /**
     * Constructor which receives a intersectableList of geometric objects and create an LinkdList of Intersectable interface with it
     * @param geometries geometric object
     */
    public Geometries(Intersectable... geometries){
        intersectableList =new LinkedList<>(Arrays.asList(geometries));
    }

    /**
     * Function to add an object to the intersectableList
     * @param geometries geometric object
     */
    public void add(Intersectable... geometries){
        this.intersectableList.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> intersections=null;
        for (Intersectable item: intersectableList
             ) {
            List<Point> current=item.findIntsersections(ray);
            if (current!=null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(current);
            }
        }
        return intersections;
    }
}
