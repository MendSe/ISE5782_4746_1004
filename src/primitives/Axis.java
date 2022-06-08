package primitives;

import java.util.HashMap;
import java.util.Map;

/**
 * Axis enum for the bounding box
 */
public enum Axis {
    X(0), Y(1), Z(2);

    private final int index;
    private final static Map<Integer, Axis> map = new HashMap<>();

    /**
     * Static constructor that set the axis of the map
     */
    static {
        for (Axis axis : Axis.values()) {
            map.put(axis.index, axis);
        }
    }

    /**
     * Axis constructor that set the index of the map
     * @param index index from where we construct the axis
     */
    Axis(int index) {
        this.index = index;
    }

    /**
     * This function returns the index of the current node.
     *
     * @return The index of the current node.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Given an index, return the corresponding Axis.
     *
     * @param idx The index of the axis.
     * @return The value of the key.
     */
    public static Axis atIndex(int idx){
        return map.get(idx);
    }

    /**
     * Return the next axis in the enum, wrapping around to the first axis if necessary.
     *
     * @return The next axis in the enum.
     */
    public Axis getNext(){
        return map.get((this.ordinal()+1)%Axis.values().length);
    }

}
