package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera object to create rays through pixels
 */
public class Camera {
    private Point p0;

    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double distance;
    private double height;
    private double width;

    private ImageWriter imw;
    private RayTracerBase rtb;

    /**
     * Constructor that initializes the parameters of the camera object
     *
     * @param p  origin point of the camera
     * @param to direction vector
     * @param up direction vector
     * @throws IllegalArgumentException if the "to" and "up" vectors are not perpendicular
     */
    Camera(Point p, Vector to, Vector up) {
        if (!isZero(to.dotProduct(up))) //checks if the vectors are perpendicular
            throw new IllegalArgumentException("The vector are not perpendicular");

        p0 = p;
        vTo = to.normalize();
        vUp = up.normalize();
        vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Function that set the size of the View Plane
     *
     * @param width  width value of the View Plane
     * @param height height value of the View Plane
     * @return the camera object itself
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Function that set the distance between the camera and the View Plane
     *
     * @param distance the distance between the camera and the View Plane
     * @return the camera object itself
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * This function sets the image writer of the camera to the given image writer.
     *
     * @param imageWriter The image writer that will be used to write the image to a file.
     * @return The camera itself.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        imw = imageWriter;
        return this;
    }


    /**
     * This function sets the ray tracer to be used by the camera.
     *
     * @param rayTracer The ray tracer to use.
     * @return The camera object itself.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        rtb = rayTracer;
        return this;
    }

    /**
     * Construct a list of ray through a pixel on the view plane
     *
     * @param nX number of pixel in the x-axis
     * @param nY number of pixels in the y-axis
     * @param j  the index of the pixel in the x-axis
     * @param i  the index of the pixel in the y-axis
     * @return a ray from the camera going through the center of the pixel
     */
    public LinkedList<Ray> constructRay(int nX, int nY, int j, int i) {
        Point pC = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        Point pIJ = pC;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));
        LinkedList<Ray> rays=new LinkedList<>();
        Point helpP=new Point((pIJ.getX()-4/9d),(pIJ.getY()-4/9d),pIJ.getZ());  //first point of the 9x9 grid
        Point gridPoint;                                                        //current point on the grid
        for(int k=0;k<9;k++){
            for (int l=0;l<9;l++){
            gridPoint =new Point(helpP.getX()+l/9d, helpP.getY()+k/9d,helpP.getZ() );
            rays.add(new Ray(p0,gridPoint.subtract((p0))));
            }
        }
        return rays;
    }

    /**
     * This function helps us to render the image.
     * It first checks if there are not any missing resource then for each pixel it calls the castRay function to have a color
     */
    public void renderImage() {
        if (imw == null) throw new MissingResourceException("Missing resource", ImageWriter.class.getName(), "");
        if (rtb == null) throw new MissingResourceException("Missing resource", RayTracerBase.class.getName(), "");

        int nX = imw.getNx();
        int nY = imw.getNy();
        for (int i = 0; i < nY; i++)
            for (int j = 0; j < nX; j++) {
                Color pixelColor = this.castRay(j, i);
                imw.writePixel(j, i, pixelColor);
            }
    }

    /**
     * This function helps us to create a color for a coordinates in the image
     *
     * @param j coordinate in the j axis
     * @param i coordinate in the i axis
     * @return the color of the ray
     */
    private Color castRay(int j, int i) {
        LinkedList<Ray> rays = this.constructRay(imw.getNx(), imw.getNy(), j, i);
        return rtb.AverageColor(rays,j,i);
        //return rtb.traceRay(ray, j, i);
    }

    /**
     * This function helps us to prints a grid
     *
     * @param interval the interval between grid line
     * @param color    the color of the grid line
     */
    void printGrid(int interval, Color color) {
        if (this.imw == null)
            throw new MissingResourceException("Missing resource", "ImageWriter", " ");
        int nX = imw.getNx();
        int nY = imw.getNy();
        for (int i = 0; i < nY; i++)
            for (int j = 0; j < nX; j++)
                if (i % interval == 0 || j % interval == 0)
                    imw.writePixel(j, i, color);
    }

    /**
     * Write the current image to the image file
     */
    public void writeToImage() {
        if (imw == null) throw new MissingResourceException("Missing resource", Point.class.getName(), "");
        imw.writeToImage();
    }


}