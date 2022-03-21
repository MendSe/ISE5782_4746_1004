package primitives;

/**
 * This class inherits from the Point class and helps us to create an object that simulates a geometrical vector
 */
public class Vector extends Point {

    /**
     * This constructor initializes the values of the xyz variable by calling the constructor of the point class
     * It also checks if the user entered the 0 vector and in this case it will throw an exception
     *
     * @param x coordinate value
     * @param y coordinate value
     * @param z coordinate value
     */
    public Vector(double x, double y, double z) {

        this(new Double3(x, y, z));
    }

    /**
     * @param coords This variable contains the values of the coordinates x,y and z
     */
    Vector(Double3 coords) {
        super(coords);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0");
    }

    /**
     * This function add a vector to our vector and return this new vector to the main
     *
     * @param vector2 second vector
     * @return result of add
     */
    public Vector add(Vector vector2) {
        return new Vector(xyz.add(vector2.xyz));
    }


    /**
     * This function subtracts the vector2 to our vector and returns this new vector
     *
     * @param vector2 second vector
     * @return a new vector that result of the subtraction of our vector and the second vector
     */
    public Vector subtract(Vector vector2) {
        return new Vector(xyz.subtract(vector2.xyz));
    }

    /**
     * This function computes a new vector that result of the multiplication between our vector and a scalar
     *
     * @param scalar scalar
     * @return result of the multiplication of our vector by this scalar
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * This function returns the dot product between vector2 and our vector
     *
     * @param vector2 second vector
     * @return The dot product of our vector and the vector2
     */
    public double dotProduct(Vector vector2) {
        return xyz.d1 * vector2.xyz.d1 + xyz.d2 * vector2.xyz.d2 + xyz.d3 * vector2.xyz.d3;
    }

    /**
     * This functions calculates and returns the cross product between vector2 and our vector
     *
     * @param vector2 second vector
     * @return The cross product of our vector and vector2
     */
    public Vector crossProduct(Vector vector2) {
        double cx = xyz.d2 * vector2.xyz.d3 - xyz.d3 * vector2.xyz.d2;
        double cy = xyz.d3 * vector2.xyz.d1 - xyz.d1 * vector2.xyz.d3;
        double cz = xyz.d1 * vector2.xyz.d2 - xyz.d2 * vector2.xyz.d1;
        return new Vector(cx, cy, cz);
    }

    /**
     * This function calculates the square length of the vector and returns it
     *
     * @return The length squared of our vector
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * This function returns the length of the vector by calculating the square root of the squared length
     *
     * @return The length of our vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * This functions returns the normalized vector of the vector
     *
     * @return A new vector that's the normalized version of our vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    /**
     * This function keeps the DRY concept by using the equal of the father class
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Vector:" + super.toString();
    }


}
