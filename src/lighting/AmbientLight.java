package lighting;

import primitives.Color;
import primitives.Double3;


public class AmbientLight {

    final private Color intensity;

    public AmbientLight(Color iA,Double3 kA){
        intensity=iA.scale(kA);
    }
    public AmbientLight(){
        intensity=Color.BLACK;
    }
    public Color getIntensity(){
        return intensity;
    }
}
