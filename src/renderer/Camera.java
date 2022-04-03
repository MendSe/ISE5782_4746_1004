package renderer;

import primitives.*;

import static primitives.Util.isZero;

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

    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = p0.add(vto.scale(_distance));
        double rY = _height / nY;
        double rX = _width / nX;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        Point pIJ = pC;
        if(isZero(xJ)&& isZero(yI))
            return new Ray(p0, pIJ.subtract(p0));
        if(isZero(xJ)) {
            pIJ = pIJ.add(vup.scale(yI));
            return new Ray (p0,pIJ.subtract(p0));
        }
        if(isZero(yI)){
            pIJ = pIJ.add(vright.scale(xJ));
            return new Ray(p0,pIJ.subtract(p0));
        }

        pIJ = pIJ.add(vup.scale(yI).add(vright.scale(xJ)));
        Vector vIJ = pIJ.subtract(p0);

        return new Ray(p0,vIJ);
    }
}
