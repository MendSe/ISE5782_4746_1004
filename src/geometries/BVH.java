package geometries;

import primitives.*;

import java.util.*;

/**
 * Class which creates a BVHNode hierarchy to use BVH
 */
public class BVH extends Intersectable {

    private final Intersectable root;

    /**
     * Constructs the BVH from a given intersectable.
     */
    public BVH(Intersectable... intersectables) {
        root = generateRoot(Arrays.asList(intersectables), Axis.X);
    }


    /**
     * It splits the list of intersectable in half, and recursively generates the left and right children of the root node
     *
     * @param intersectables The list of intersectables to be put into the BVH
     * @param axis The axis to split the intersectables on.
     * @return The root of the BVH tree.
     */
    private static Intersectable generateRoot(List<Intersectable> intersectables, Axis axis) {
        if (intersectables.size() == 1) {
            return intersectables.get(0);
        }

        Comparator<Intersectable> comparator =  getComparatorByAxis(axis);
        Intersectable median = new Median<>(intersectables, comparator).findMedian();

        // Splits the intersectables around the median
        int maxLeftSize = (intersectables.size() + 1) / 2;
        List<Intersectable> left = new ArrayList<>(maxLeftSize);
        List<Intersectable> right = new ArrayList<>(intersectables.size() - maxLeftSize);
        for (Intersectable intersectable : intersectables) {
            // We need to ensure no less than half go to the right (happens when many are equal)
            if (comparator.compare(intersectable, median) <= 0 && left.size() < maxLeftSize) {
                left.add(intersectable);
            } else {
                right.add(intersectable);
            }
        }

        // Generate the node's children with the next axis
        Axis nextAxis = axis.getNext();
        return new BVHNode(
                generateRoot(left, nextAxis),
                generateRoot(right, nextAxis));
    }

    /**
     * It returns a comparator that compares two objects by their center's position along a given axis
     *
     * @param axis The axis to sort by.
     * @return A comparator that compares two Intersectable objects by their center points along a given axis.
     */
    static Comparator<Intersectable> getComparatorByAxis(Axis axis) {
        return (bb1, bb2) -> {
            Point center1 = bb1.getBoundingBoxCenter();
            if (center1 == null) {
                return 0;
            }

            Point center2 = bb2.getBoundingBoxCenter();
            if (center2 == null) {
                return 0;
            }

            return Double.compare(center1.get(axis), center2.get(axis));
        };
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        BoundingBox bb = getBoundingBox();
        if (bb != null && !bb.intersecting(ray)) {
            return null;
        }
        return root.findGeoIntersectionsHelper(ray, maxDistance);
    }

    @Override
    public BoundingBox getBoundingBox() {
        return root.getBoundingBox();
    }
}
