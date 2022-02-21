package primitives;

import static primitives.Util.isZero;


public class Vector extends Point{

    public Vector(double x1,double x2,double x3)
    {
        super(x1,x2,x3);
        if(isZero(length()))
            throw new IllegalArgumentException("ורטור אפס");

    }

    public Vector add(Vector vector2){
        Double3 help = xyz.add(vector2.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public Vector subtract(Vector vector2)
    {
        Double3 help = xyz.subtract(vector2.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public Vector Scale(double scalar)
    {
        //
        Double3 help= xyz.scale(scalar);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public double dotProduct(Vector vector2)
    {
        Double3 help = xyz.product(vector2.xyz);
        return help.d1+ help.d2+ help.d3;
    }

    public Vector crossProduct(Vector vector2)
    {
        double cx= xyz.d2*vector2.xyz.d3- xyz.d3*vector2.xyz.d2;
        double cy = xyz.d3*vector2.xyz.d1- xyz.d1*vector2.xyz.d3;
        double cz = xyz.d1*vector2.xyz.d2 - xyz.d2*vector2.xyz.d1;
        return new Vector(cx,cy,cz);
    }

    public double lengthSquared()
    {
        return xyz.d1* xyz.d1+ xyz.d2* xyz.d2+ xyz.d3* xyz.d3;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize()
    {
        double helpx,helpy,helpz;
        double taille = length();//taille = length in french

        helpx= xyz.d1/taille;
        helpy = xyz.d2/taille;
        helpz = xyz.d3/taille;

        return new Vector(helpx,helpy,helpy);
    }

    @Override
    public String toString() {
        return "Vector{}"+ super.toString();
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }


}
