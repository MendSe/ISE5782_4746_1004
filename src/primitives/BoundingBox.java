package primitives;

import java.util.Arrays;
import java.util.Objects;

/**
 * Bounding Box class to surround object
 */
public class BoundingBox {
    private Point min;
    private Point max;

    /**
     * Constructor that creates a Bounding Box from 2 points
     *
     * @param mini min point
     * @param maxi max point
     */
    public BoundingBox(Point mini, Point maxi) {
        if (mini.getX() > maxi.getX() || mini.getY() > maxi.getY() || mini.getZ() > maxi.getZ())
            throw new IllegalStateException("Min is greater than max");

        min = mini;
        max = maxi;
    }

    /**
     * Returns the minimum point of the bounding box.
     *
     * @return The minimum point of the rectangle.
     */
    public Point getMin() {
        return this.min;
    }

    /**
     * This function returns the max point of the bounding box.
     *
     * @return The max point of the bounding box.
     */
    public Point getMax() {
        return this.max;
    }

    /**
     * Return the center of the bounding box.
     *
     * @return The center of the bounding box.
     */
    public Point getCenter() {
        return min.getCenter(max);
    }

    /**
     * Helper class that saves the min and max values
     */
    public static class MinMax {
        public double min;
        public double max;
    }

    /**
     * Checks if the ray intersect the Bonding Box
     *
     * @param ray The ray to check for intersection with the bounding box.
     * @return  a boolean value.
     */
    public boolean intersecting(Ray ray) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();

        MinMax tx = calculateMM(min.getX(), max.getX(), p0.getX(), dir.getX());
        MinMax ty = calculateMM(min.getY(), max.getY(), p0.getY(), dir.getY());
        MinMax tz = calculateMM(min.getZ(), max.getZ(), p0.getZ(), dir.getZ());

        return tx.min <= ty.max && tx.min <= tz.max &&
                ty.min <= tx.max && ty.min <= tz.max &&
                tz.min <= tx.max && tz.min <= ty.max;
    }

    /**
     * If the ray is pointing in the positive direction, the minimum value is the distance from the origin to the minimum
     * point, and the maximum value is the distance from the origin to the maximum point. If the ray is pointing in the
     * negative direction, the minimum value is the distance from the origin to the maximum point, and the maximum value is
     * the distance from the origin to the minimum point
     *
     * @param aMin   the minimum value of the axis
     * @param aMax   the maximum value of the axis
     * @param origin the origin of the ray
     * @param dir    the direction of the ray
     * @return The minimum and maximum values of the ray.
     */
    private MinMax calculateMM(double aMin, double aMax, double origin, double dir) {
        MinMax mm = new MinMax();
        double a = 1 / dir;

        if (a >= 0) {
            mm.min = (aMin - origin) * a;
            mm.max = (aMax - origin) * a;
        } else {
            mm.max = (aMin - origin) * a;
            mm.min = (aMax - origin) * a;
        }
        return mm;
    }


    /**
     * Given an array of bounding boxes, return a bounding box that surrounds all of them.
     * The first thing we do is filter out any null bounding boxes. If there are no bounding boxes left, we return null
     *
     * @return A new BoundingBox object that surrounds all the BoundingBox objects in the array.
     */
    public static BoundingBox surround(BoundingBox... bb) {
        if (bb.length == 0) return null;

        BoundingBox[] bb1 = Arrays.stream(bb)
                .filter(Objects::nonNull)
                .toArray(BoundingBox[]::new);

        if (bb1.length == 0) return null;

        return new BoundingBox(
                new Point(
                        Point.getMin(Axis.X,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMin)
                                        .toArray(Point[]::new)),
                        Point.getMin(Axis.Y,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMin)
                                        .toArray(Point[]::new)),
                        Point.getMin(Axis.Z,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMin)
                                        .toArray(Point[]::new))
                ),
                new Point(
                        Point.getMax(Axis.X,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMax)
                                        .toArray(Point[]::new)),
                        Point.getMax(Axis.Y,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMax)
                                        .toArray(Point[]::new)),
                        Point.getMax(Axis.Z,
                                Arrays.stream(bb1)
                                        .map(BoundingBox::getMax)
                                        .toArray(Point[]::new))
                )
        );
    }

}
