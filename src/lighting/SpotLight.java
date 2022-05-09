package lighting;
import com.sun.jdi.connect.Transport;
import primitives.*;

public class SpotLight extends PointLight{
    private Vector direction;

    public SpotLight(Color intensity,Point position, Vector direction){
        super(intensity,position);
        this.direction = direction.normalize();
    }

}
