package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {


    /**
     * 所有点
     */
    Map<Integer,Node> nodes;

    /**
     * 所有边
     */
    HashSet<Edge> edges;


    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
