package primitives;

import static primitives.Util.isZero;


public class Vector extends Point{

    /**
     *This constructor initializes the values of the xyz variable by calling the constructor of the point class
     * It also checks if the user entered the 0 vector and in this case it will throw an exception
     *
     */
    public Vector(double x1,double x2,double x3)
    {
        super(x1,x2,x3);
        if(isZero(length()))
            throw new IllegalArgumentException("Vector 0");

    }

    /**
    * This function add a vector to our vector and return this new vector to the main
    * */
    public Vector add(Vector vector2){
        Double3 help = xyz.add(vector2.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }


    /**
    * This function subtracts the vector2 to our vector and returns this new vector
    * */
    public Vector subtract(Vector vector2)
    {
        Double3 help = xyz.subtract(vector2.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }

    /**
    * This function calculates the product of the scalar and our vector and then returns it
    * */
    public Vector Scale(double scalar)
    {
        Double3 help= xyz.scale(scalar);
        return new Vector(help.d1, help.d2, help.d3);
    }

    /**
     * This function returns the dot product between vector2 and our vector
     * */
    public double dotProduct(Vector vector2)
    {
        Double3 help = xyz.product(vector2.xyz);
        return help.d1+ help.d2+ help.d3;
    }

    /**
     * This functions calculates and returns the cross product between vector2 and our vector
     * */
    public Vector crossProduct(Vector vector2)
    {
        double cx= xyz.d2*vector2.xyz.d3- xyz.d3*vector2.xyz.d2;
        double cy = xyz.d3*vector2.xyz.d1- xyz.d1*vector2.xyz.d3;
        double cz = xyz.d1*vector2.xyz.d2 - xyz.d2*vector2.xyz.d1;
        return new Vector(cx,cy,cz);
    }

    /**
     * This function calculates the square length of the vector and returns it
     * */
    public double lengthSquared()
    {
        return (xyz.d1* xyz.d1)+ (xyz.d2* xyz.d2)+ (xyz.d3* xyz.d3);
    }

    /**
     * This function returns the length of the vector by calculating the square root of the squared length
     * */
    public double length()
    {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * This functions returns the normalized vector of the vector
     * */
    public Vector normalize()
    {
        double helpx,helpy,helpz;
        double length = length();

        helpx= xyz.d1/length;
        helpy = xyz.d2/length;
        helpz = xyz.d3/length;
        return new Vector(helpx,helpy,helpz);
    }

    /**
     * This function keeps the DRY concept by using the equal of the father class
     * */
    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Vector:"+ super.toString();
    }


}
