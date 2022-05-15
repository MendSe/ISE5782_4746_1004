package scene;

import geometries.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import lighting.*;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.MissingResourceException;

/**
 * Scene class responsible for the background, the ambient light and the geometries of the environment
 */
public class Scene {

    public String name;//Name of the scene
    public Color background = Color.BLACK;// Color of the background of the scene
    public AmbientLight ambientLight = new AmbientLight(); //Ambient light of the scene
    public Geometries geometries = new Geometries(); //Geometric figure depicted in the scene
    public List<LightSource> lights = new LinkedList<>();//List of the light source in the scene

    /**
     * constructor
     *
     * @param name string
     */
    public Scene(String name) {
        this.name = name;
    }


    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light of the scene.
     * @return The Scene object itself.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    /**
     * class Builder from Builder design pattern responsible for initializing a scene
     */
    public static class Builder {
        private final Scene scene;

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
         * setter for ambient light
         *
         * @param ambientLight AmbientLight
         * @return the scene builder
         */
        public Builder setAmbientLight(AmbientLight ambientLight) {
            scene.ambientLight = ambientLight;
            return this;
        }


        /**
         * This function sets the lights of the scene to the given list of lights.
         *
         * @param lights A list of light sources.
         * @return The Builder object itself.
         */
        public Builder setLights(List<LightSource> lights) {
            scene.lights = lights;
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
    public Scene xmlParse(){
        Scene.Builder builder = new Scene.Builder("Test scene");//
        Scene scene =null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(System.getProperty("user.dir") + "/xml/basicRenderTestTwoColors.xml")); //path of the document
            doc.getDocumentElement().normalize(); //Normalizing the document helps generate correct results.

            NodeList list = doc.getElementsByTagName("scene");	//Node list of the scene elements
            Node node = list.item(0);
            Element element = (Element) node;
            //Element element =doc.getDocumentElement();

            String[] current=element.getAttribute("background-color").split(" "); //color values stored in the array string "current"
            builder.setBackground(new Color(Double.parseDouble(current[0]),Double.parseDouble(current[1]),Double.parseDouble(current[2])));

            list=doc.getElementsByTagName("ambient-light");		//Node list of the ambient-light elements
            node=list.item(0);
            element=(Element)node;

            current=element.getAttribute("color").split(" ");					  //color values stored in the array string "current"
            builder.setAmbientLight(new AmbientLight(new Color(Double.parseDouble(current[0]),Double.parseDouble(current[1]),Double.parseDouble(current[2])),new Double3(1,1,1)));

            scene=builder.build();								//build the scene

            list=doc.getElementsByTagName("geometries");		//Node list of the geometries elements
            node=list.item(0);
            element=(Element)node;
            NodeList children = element.getChildNodes();
            for(int i=0;i<children.getLength();i++){			//for each geometry in geometries
                node=children.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) {	//if the node isn't what we are looking for
                    continue;
                }
                element=(Element) node;

                switch (element.getNodeName()) {
                    case "sphere" -> {
                        current = element.getAttribute("center").split(" ");
                        double radius = Double.parseDouble(element.getAttribute("radius"));
                        scene.geometries.add(new Sphere(new Point(Double.parseDouble(current[0]), Double.parseDouble(current[1]), Double.parseDouble(current[2])), radius));
                    }
                    case "triangle" -> {
                        Point[] points = new Point[element.getAttributes().getLength()];
                        for (int j = 0; j < points.length; j++) {
                            current = element.getAttribute("p" + j).split(" ");
                            points[j] = new Point(Double.parseDouble(current[0]), Double.parseDouble(current[1]), Double.parseDouble(current[2]));
                        }
                        scene.geometries.add(new Triangle(points[0], points[1], points[2]));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + element.getNodeName());
                }
            }


        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return scene;
    }
} // class Scene
