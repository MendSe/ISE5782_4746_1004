package primitives;

public class Point {
     final Double3 coordinates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return coordinates != null ? coordinates.equals(point.coordinates) : point.coordinates == null;
    }

    @Override
    public int hashCode() {
        return coordinates != null ? coordinates.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates +
                '}';
    }

    /*
    public void setCoordinates(Double3 coordinates) {
        this.coordinates = coordinates;
    }*/

    public Point(double x1, double x2, double x3){
        coordinates = new Double3(x1,x2,x3);
    }

    public Point Substract(Point point2)
    {
        Point newvector = null;
        newvector.coordinates.subtract(point2.coordinates);
        return newvector;
    }

    public Point Add(Point point2)
    {
        Point newvector = null;
        newvector.coordinates.add(point2.coordinates);
        return newvector;
    }

    public double DistanceSquared(Point point2)
    {
       double x= (coordinates.d1-point2.coordinates.d1)*(coordinates.d1-point2.coordinates.d1);
       double y=(coordinates.d2-point2.coordinates.d2)*(coordinates.d2-point2.coordinates.d2);
       double z = (coordinates.d3-point2.coordinates.d3)*(coordinates.d3-point2.coordinates.d3);
       return x+y+z;
    }

    public double Distance(Point point2)
    {
        return Math.sqrt(DistanceSquared(point2));
    }







}
