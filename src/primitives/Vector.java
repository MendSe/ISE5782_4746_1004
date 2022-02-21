package primitives;
import primitives.*;

import static primitives.Util.isZero;


public class Vector extends Point{

    public Vector(double x1,double x2,double x3)
    {
        System.out.printf("Je roucoule lA LANGUE DE MOLIERE");
        super(x1,x2,x3);
        if(isZero(length()))
            throw new IllegalArgumentException("ורטור אפס");

    }

    public Vector add(Vector vector2){
        Double3 help = coordinates.add(vector2.coordinates);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public Vector substract(Vector vector2)
    {
        Double3 help = coordinates.subtract(vector2.coordinates);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public Vector Scale(double scalar)
    {
        //
        Double3 help= coordinates.scale(scalar);
        return new Vector(help.d1, help.d2, help.d3);
    }

    public double dotProduct(Vector vector2)
    {
        Double3 help = coordinates.product(vector2.coordinates);
        return help.d1+ help.d2+ help.d3;
    }

    public Vector crossProduct(Vector vector2)
    {


        return null;
    }

    public double length()
    {
        return 0;
    }
}
