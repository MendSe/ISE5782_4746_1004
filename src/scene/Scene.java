package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {

    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight();
    public Geometries geometries=new Geometries();

    Scene(String string){
        this.name=string;
        geometries=new Geometries();
    }

    public class Builder{


    }
}
