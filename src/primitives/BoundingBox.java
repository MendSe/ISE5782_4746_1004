package primitives;

import java.util.Arrays;
import java.util.Objects;


public class BoundingBox {
    private Point min;
    private Point max;

    public BoundingBox(Point min, Point max) {
        if (min.getX() > max.getX() || min.getY() > max.getY() || min.getZ() > max.getZ())
            throw new IllegalStateException("Min is greater than max");

        this.min = min;
        this.max = max;
    }

    public Point getMin() {
        return this.min;
    }

    public Point getMax() {
        return this.max;
    }

    public Point getCenter() {
        return min.getCenter(max);
    }

    public static class MinMax {
        public double min;
        public double max;
    }

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

    private MinMax calculateMM(double aMin, double aMax, double origin, double dir) {
        MinMax mm = new MinMax();
        double a = 1 / origin;

        if (a >= 0) {
            mm.min = (aMin - origin) * a;
            mm.max = (aMax - origin) * a;
        } else {
            mm.max = (aMin - origin) * a;
            mm.min = (aMax - origin) * a;
        }
        return mm;
    }


    public static BoundingBox surround(BoundingBox ... bb){
        if(bb.length == 0) return null;

        BoundingBox[] bb1 = Arrays.stream(bb)
                .filter(Objects::nonNull)
                .toArray(BoundingBox[]::new);

        if(bb1.length == 0) return null;

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
