package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    List<Intersectable[]> list;

    /**
     * Default constructor
     */
    public Geometries(){
        this.list=new LinkedList<Intersectable[]>();
    }

    /**
     * Constructor pppppppppppppppppppp
     * @param geometries geometric object
     */
    public Geometries(Intersectable... geometries){
        this.list=new LinkedList<Intersectable[]>(Collections.singleton(geometries));
    }

    /**
     * Function to add an object to the list
     * @param geometries geometric object
     */
    public void add(Intersectable... geometries){
        this.list.add(geometries);
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
