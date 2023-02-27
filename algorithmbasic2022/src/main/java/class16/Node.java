package class16;

import java.util.ArrayList;

/**
 * 点结构描述
 */
public class Node {


    public int value ;
    public int in ;
    public int out ;

    /**
     * 点出发的节点
     */
    public ArrayList<Node> nexts;

    public ArrayList<Edge> edges;


    public Node (int value){
        this.value= value ;
        in =0 ;
        out =0 ;
        nexts =  new ArrayList<>();
        edges =  new ArrayList<>();
    }
}
