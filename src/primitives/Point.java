package primitives;

/**
 * Point class represents a point with 3 coordinates
 */
public class Point {
    final protected Double3 xyz;

    /**
     * getter of coordinate x
     *
     * @return coordinate x value
     */
    public double getX() {
        return this.xyz.d1;
    }

    /**
     * getter of coordinate y
     *
     * @return coordinate y value
     */
    public double getY() {
        return this.xyz.d2;
    }

    /**
     * getter of coordinate z
     *
     * @return coordinate z value
     */
    public double getZ() {
        return this.xyz.d3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "Point:" +
                xyz;
    }

    /**
     * Constructor which initializes the Point object with the values of the coordinates
     *
     * @param x coordinate value
     * @param y coordinate value
     * @param z coordinate value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor which initializes the Point object with set of 3 values of the coordinates
     *
     * @param xyz values triad
     */
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * This function returns a vector that's result of the subtraction of the point2 and xyz
     *
     * @param p1 point value
     * @return Vector
     */
    public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }

    /**
     * This function add a vector to the point to return a point
     *
     * @param vec vector value
     * @return Point
     */
    public Point add(Vector vec) {
        return new Point(xyz.add(vec.xyz));
    }

    /**
     * This function returns the squared distance between 2 points
     *
     * @param point2 point coord value
     * @return double
     */
    public double distanceSquared(Point point2) {
        double dx = xyz.d1 - point2.xyz.d1;
        double dy = xyz.d2 - point2.xyz.d2;
        double dz = xyz.d3 - point2.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * This function returns the distance between 2 points by computing the square root of the squared distance
     *
     * @param point2 point coord value
     * @return double
     */
    public double distance(Point point2) {
        return Math.sqrt(distanceSquared(point2));
    }

}
