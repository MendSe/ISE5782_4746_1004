package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.util.MissingResourceException;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();

    public Scene(String string) {
        this.name = string;
        geometries = new Geometries();
    }


    public static class Builder {
        private Scene scene = null;

        public Builder(String name) {
            scene = new Scene(name);
        }

        public Builder reset(String name) {
            scene = new Scene(name);
            return this;
        }

        public Builder setBackground(Color color) {
            scene.background = color;
            return this;
        }

        public Builder setAmbientLight(AmbientLight ambientLight) {
            scene.ambientLight = ambientLight;
            return this;
        }

        public Builder addGeometry(Intersectable geo) {
            if (scene.geometries == null) scene.geometries = new Geometries();
            scene.geometries.add(geo);
            return this;
        }

        public Scene build() {
            if (scene.name == null || scene.geometries == null)
                throw new MissingResourceException("No name or no objects in the scene", Scene.class.getName(),"");
            return scene;
        }
    } // class Builder
} // class Scene
