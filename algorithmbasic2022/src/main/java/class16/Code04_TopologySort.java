package class16;

import java.util.*;

public class Code04_TopologySort {

    public static List<Node> sortedTopology(Graph graph){

        HashMap<Node,Integer> inMap = new HashMap<>();

        //剩余入度为0
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()){
            inMap.put(node,node.in);
            if (node.in==0){
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while(!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts){
                inMap.put(next, inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }

        return result;
    }
}
