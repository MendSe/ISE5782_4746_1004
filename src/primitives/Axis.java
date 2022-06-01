package primitives;

import java.util.HashMap;
import java.util.Map;

public enum Axis {
    X(0), Y(1), Z(2);

    private final int index;
    private final static Map<Integer, Axis> map = new HashMap<>();

    static {
        for (Axis axis : Axis.values()) {
            map.put(axis.index, axis);
        }
    }

    Axis(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static Axis atIndex(int idx){
        return map.get(idx);
    }

    public Axis getNext(){
        return map.get((this.ordinal()+1)%Axis.values().length);
    }

}
