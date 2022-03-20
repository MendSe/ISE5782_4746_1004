package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private final List<Intersectable> list;

    /**
     * Default constructor
     */
    public Geometries(){
        this.list=new LinkedList<>();
    }

    /**
     * Constructor which receives a list of geometric objects and create an LinkdList of Intersectable interface with it
     * @param geometries geometric object
     */
    public Geometries(Intersectable... geometries){
        list=new LinkedList<>(Arrays.asList(geometries));
    }

    /**
     * Function to add an object to the list
     * @param geometries geometric object
     */
    public void add(Intersectable... geometries){
        this.list.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> intersections=null;
        for (Intersectable item:list
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
