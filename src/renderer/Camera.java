package renderer;

import primitives.*;

public class Camera {
    private Point p0;
    private Vector vto, vup, vright;

    private double _distance;
    private double _height;
    private double _width;

    Camera(Point p, Vector to, Vector up) {
        if (to.dotProduct(up) != 0)
            throw new IllegalArgumentException("The vector are not perpendicular");

        p0 = p;
        vto = to.normalize();
        vup = up.normalize();
        vright = vto.crossProduct(vup).normalize();
    }

    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;

        return this;
    }

}
