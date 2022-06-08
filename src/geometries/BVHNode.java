package geometries;

import primitives.BoundingBox;
import primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class BVHNode extends  Intersectable{

        private final Intersectable left, right;
        private final BoundingBox box;

        /**
         * Constructs a new Node with a given children.
         */
        public BVHNode(Intersectable l, Intersectable r) {
            left = l;
            right = r;
            box = BoundingBox.surround(
                   left.getBoundingBox(),
                    right.getBoundingBox());
        }

        @Override
        public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

            BoundingBox bb = getBoundingBox();
            if (bb != null && !bb.intersecting(ray)) {
                return null;
            }
            List<GeoPoint> l = left.findGeoIntersections(ray, maxDistance);
            List<GeoPoint> r = right.findGeoIntersections(ray, maxDistance);
            if (l == null) {
                return r;
            }
            if (r == null) {
                return l;
            }

            // Concatenates the intersections points' lists
            return Stream.concat(l.stream(), r.stream())
                    .collect(Collectors.toList());
        }


        @Override
        public BoundingBox getBoundingBox() {
            return box;
        }
    }
