package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera object to create rays through pixels
 */
public class Camera {
    private Point p0;

    private Vector vto;
    private Vector vup;
    private Vector vright;

    private double _distance;
    private double _height;
    private double _width;

    private ImageWriter imw;
    private RayTracerBase rtb;

    /**
     * Constructor that initializes the parameters of the camera object
     *
     * @param p  origin point of the camera
     * @param to direction vector
     * @param up direction vector
     */
    Camera(Point p, Vector to, Vector up) {
        if (to.dotProduct(up) != 0)//checks if the vectors are perpendicular
            throw new IllegalArgumentException("The vector are not perpendicular");

        p0 = p;
        vto = to.normalize();
        vup = up.normalize();
        vright = vto.crossProduct(vup).normalize();
    }

    /**
     * Function that set the size of the View Plane
     *
     * @param width  width value of the View Plane
     * @param height height value of the View Plane
     * @return the camera object itself
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * Function that set the distance between the camera and the View Plane
     *
     * @param distance the distance between the camera and the View Plane
     * @return the camera object itself
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        imw = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracer) {
        rtb = rayTracer;
        return this;
    }

    /**
     * Construct a ray through a pixel on the view plane
     *
     * @param nX number of pixel in the x-axis
     * @param nY number of pixels in the y-axis
     * @param j  the index of the pixel in the x-axis
     * @param i  the index of the pixel in the y-axis
     * @return a ray from the camera going through the center of the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = p0.add(vto.scale(_distance));
        double rY = _height / nY;
        double rX = _width / nX;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        Point pIJ = pC;
        if (isZero(xJ) && isZero(yI))
            return new Ray(p0, pIJ.subtract(p0));
        if (isZero(xJ)) {
            pIJ = pIJ.add(vup.scale(yI));
            return new Ray(p0, pIJ.subtract(p0));
        }
        if (isZero(yI)) {
            pIJ = pIJ.add(vright.scale(xJ));
            return new Ray(p0, pIJ.subtract(p0));
        }

        pIJ = pIJ.add(vup.scale(yI).add(vright.scale(xJ)));
        Vector vIJ = pIJ.subtract(p0);

        return new Ray(p0, vIJ);
    }

    public void renderImage() {
        try {
            if (p0 == null) throw new MissingResourceException("Missing ressource", Point.class.getName(), "");

            if (vto == null) throw new MissingResourceException("Missing ressource", Vector.class.getName(), "");
            if (vup == null) throw new MissingResourceException("Missing ressource", Vector.class.getName(), "");
            if (vright == null) throw new MissingResourceException("Missing ressource", Vector.class.getName(), "");

            if (imw == null) throw new MissingResourceException("Missing ressource", ImageWriter.class.getName(), "");
            if (rtb == null) throw new MissingResourceException("Missing ressource", RayTracerBase.class.getName(), "");

        } catch (MissingResourceException e) {
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Yes");
    }
    public void writeToImage(){
        if (imw==null)throw new MissingResourceException("Missing ressource",Point.class.getName(),"");
        imw.writeToImage();
    }
}