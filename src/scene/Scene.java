package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();

    Scene(String string) {
        this.name = string;
        geometries = new Geometries();
    }

    public static class Builder {
        private Scene scene = null;

        public Builder(String name) {
            scene = new Scene(name);
        }

        public static Builder reset(String name) {
            scene = new Scene(name);
            return this;
        }

        public Builder setBackground(Color color) {
            scene.background = color;
            return this;
        }

        public Builder setAmbient(Color color, Double3 ka) {
            scene.ambientLight = new AmbientLight(color, ka);
            return this;
        }

        public Builder addGeometry(Intersectable geo) {
            if (scene.geometries == null) scene.geometries = new Geometries();
            scene.geometries.add(geo);
            return this;
        }

        public Scene build() {
            if (scene.name == null || scene.geometries == null)
                throw new IllegalArgumentException("No name or no objects in the scene");
            return scene;
        }
    } // class Builder
} // class Scene
