package primitives;

public class Material {

    Double3 kD=null,kS=null;
    int nShininess=0;


    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }


    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }


    public Material setShininess(int shininess) {
        this.nShininess = shininess;
        return this;
    }
}
