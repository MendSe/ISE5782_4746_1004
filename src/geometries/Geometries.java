package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Class geometries to compile geometrical objects in a intersectableList
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> intersectableList = new LinkedList<>();
    private BoundingBox box;

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
        box=buildBoundingBox();
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        BoundingBox bb = getBoundingBox();
        if (bb != null && !bb.intersecting(ray)) {
            return null;
        }

        List<GeoPoint> intersections = null;
        for (Intersectable item : intersectableList) {
            List<GeoPoint> current = item.findGeoIntersections(ray, maxDistance);
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
    public BoundingBox getBoundingBox(){
        return box;
    }

    /**
     * Build a bounding box that surrounds all the bounding boxes of the intersectable objects in the list.
     *
     * @return A BoundingBox that surrounds all the BoundingBoxes of the Intersectables in the list.
     */
    private BoundingBox buildBoundingBox() {
        return BoundingBox.surround(
                intersectableList.stream()
                        .map(Intersectable::getBoundingBox)
                        .toArray(BoundingBox[]::new)
        );
    }

}
