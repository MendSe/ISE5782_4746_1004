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
    public int hashCode() {
        return xyz != null ? xyz.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /*
    public void setCoordinates(Double3 xyz) {
        this.xyz = xyz;
    }*/

    public Point(double x1, double x2, double x3){
        xyz = new Double3(x1,x2,x3);
    }

    public Point subtract(Point point2)
    {
        Point newvector = null;
        newvector.xyz.subtract(point2.xyz);
        return newvector;
    }

    public Point add(Point point2)
    {
        Point newvector = null;
        newvector.xyz.add(point2.xyz);
        return newvector;
    }

    public double DistanceSquared(Point point2)
    {
       double x= (xyz.d1-point2.xyz.d1)*(xyz.d1-point2.xyz.d1);
       double y=(xyz.d2-point2.xyz.d2)*(xyz.d2-point2.xyz.d2);
       double z = (xyz.d3-point2.xyz.d3)*(xyz.d3-point2.xyz.d3);
       return x+y+z;
    }

    public double Distance(Point point2)
    {
        return Math.sqrt(DistanceSquared(point2));
    }







}
