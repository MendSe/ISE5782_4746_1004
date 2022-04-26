package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.util.MissingResourceException;

/**
 * Scene class responsable of the background, the ambientlight and the geometries of the environment
 */
public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();

    /**
     * constructor
     *
     * @param name string
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * class Builder from Builder design pattern responsable of initializing a scene
     */
    public static class Builder {
        private Scene scene = null;

        /**
         * constructor which uses the constructor of Scene to initialize the scene
         *
         * @param name string
         */
        public Builder(String name) {
            scene = new Scene(name);
        }

        /**
         * setter for background
         *
         * @param color Color
         * @return the scene builder
         */
        public Builder setBackground(Color color) {
            scene.background = color;
            return this;
        }

        /**
         * setter for ambientlight
         *
         * @param ambientLight AmbientLight
         * @return the scene builder
         */
        public Builder setAmbientLight(AmbientLight ambientLight) {
            scene.ambientLight = ambientLight;
            return this;
        }

        /**
         * build and return the scene
         *
         * @return the scene
         */
        public Scene build() {
            if (scene.name == null || scene.geometries == null)
                throw new MissingResourceException("No name or no objects in the scene", Scene.class.getName(), "");
            return scene;
        }
    } // class Builder
} // class Scene
