package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Class geometries to compile geometrical objects in a intersectableList
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> intersectableList = new LinkedList<>();

    /**
     * Constructor which receives a intersectableList of geometric objects and create an LinkedList of Intersectable interface with it
     *
     * @param geometries geometric object
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Function to add an object to the intersectableList
     *
     * @param geometries geometric object
     */
    public void add(Intersectable... geometries) {
        if (geometries.length != 0) this.intersectableList.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable item : intersectableList) {
            List<Point> current = item.findIntsersections(ray);
            if (current != null) {
                if (intersections == null)
                    intersections = new LinkedList<>(current);
                else
                    intersections.addAll(current);
            }
        }
        return intersections;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionHelper(Ray ray)
    {
        List<GeoPoint> intersections = null;
        for(Intersectable item : intersectableList){
            List<GeoPoint> current = item.findGeoIntersections(ray);
            if(current != null){
                if(intersections == null)
                    intersections = new LinkedList<>(current);
                else intersections.addAll(current);
            }
        }

        return intersections;
    }

}
