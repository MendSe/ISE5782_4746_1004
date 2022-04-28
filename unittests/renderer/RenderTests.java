package renderer;

import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import primitives.*;
import renderer.*;
import scene.Scene;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene.Builder("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1,1,1))) //
				.setBackground(new Color(75, 127, 90))
				.build();

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
				// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
				// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
		Camera camera = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))				
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene("XML Test scene");
		// enter XML file name and parse from XML file into scene object
		// ...
		scene=xmlParse();
		Camera camera = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500)
				.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}
	public Scene xmlParse(){
		Scene.Builder builder = new Scene.Builder("Test scene");//
		Scene scene =null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(System.getProperty("user.dir") + "/xml/basicRenderTestTwoColors.xml")); //path of the document
			doc.getDocumentElement().normalize(); //Normalizing the document helps generate correct results.

			NodeList list = doc.getElementsByTagName("scene");	//Nodelist of the scene elements
			Node node = list.item(0);
			Element element = (Element) node;
			//Element element =doc.getDocumentElement();

			String[] current=element.getAttribute("background-color").split(" "); //color values stored in the array string "current"
			builder.setBackground(new Color(Double.parseDouble(current[0]),Double.parseDouble(current[1]),Double.parseDouble(current[2])));

			list=doc.getElementsByTagName("ambient-light");		//Nodelist of the ambient-light elements
			node=list.item(0);
			element=(Element)node;

			current=element.getAttribute("color").split(" ");					  //color values stored in the array string "current"
			builder.setAmbientLight(new AmbientLight(new Color(Double.parseDouble(current[0]),Double.parseDouble(current[1]),Double.parseDouble(current[2])),new Double3(1,1,1)));

			scene=builder.build();								//build the scene

			list=doc.getElementsByTagName("geometries");		//Nodelist of the geometries elements
			node=list.item(0);
			element=(Element)node;
			NodeList children = element.getChildNodes();
			for(int i=0;i<children.getLength();i++){			//for each geometrie in geometries
				node=children.item(i);
				if (node.getNodeType() != Node.ELEMENT_NODE) {	//if the node isn't what we are looking for
					continue;
				}
				element=(Element) node;

				switch (element.getNodeName()){
					case "sphere":
						current = element.getAttribute("center").split(" ");
						double radius = Double.parseDouble(element.getAttribute("radius"));
						scene.geometries.add(new Sphere(new Point(Double.parseDouble(current[0]), Double.parseDouble(current[1]), Double.parseDouble(current[2])), radius));
						break;

					case "triangle":
						Point[] points =new Point[element.getAttributes().getLength()];
						for(int j=0;j<points.length;j++) {
							current = element.getAttribute("p"+j).split(" ");
							points[j]=new Point(Double.parseDouble(current[0]), Double.parseDouble(current[1]), Double.parseDouble(current[2]));
						}
						scene.geometries.add(new Triangle(points[0],points[1],points[2]));
						break;
				}
			}


		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return scene;
	}
	// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid

	@Test
	public void basicRenderMultiColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -100), 50),
				// up left
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
						.setEmission(new Color(GREEN)),
				// down left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(RED)),
				// down right
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
						.setEmission(new Color(BLUE)));

		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("color render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(WHITE));
		camera.writeToImage();
	}*/
}
