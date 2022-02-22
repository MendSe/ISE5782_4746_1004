package primitives;

public class Point {
     final protected Double3 xyz;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return xyz != null ? xyz.equals(point.xyz) : point.xyz == null;
    }
    @Override
    public String toString() {
        return "Point:" +
                 xyz ;
    }

    /**
     * Constructor of the class which initializes the xyz variable of the class
     * */
    public Point(double x1, double x2, double x3){
        xyz = new Double3(x1,x2,x3);
    }

    /**
     * This function returns a vector that's result of the subtraction of the point2 and xyz
     * */
    public Vector subtract(Point p1)
    {
        Double3 help = xyz.subtract(p1.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }

    /**
     * This function add a vector to the point to return a point
     * */
    public Point add(Vector vec)
    {
        Double3 help = xyz.add(vec.xyz);
        return new Point(help.d1, help.d2, help.d3);
    }

    /**
     * This function returns the squared distance between 2 points
     * */
    public double DistanceSquared(Point point2)
    {
       double x= (xyz.d1-point2.xyz.d1)*(xyz.d1-point2.xyz.d1);
       double y=(xyz.d2-point2.xyz.d2)*(xyz.d2-point2.xyz.d2);
       double z = (xyz.d3-point2.xyz.d3)*(xyz.d3-point2.xyz.d3);
       return x+y+z;
    }

    /**
     * This function returns the distance between 2 points by computing the square root of the squared distance
     * */
    public double Distance(Point point2)
    {
        return Math.sqrt(DistanceSquared(point2));
    }







}
