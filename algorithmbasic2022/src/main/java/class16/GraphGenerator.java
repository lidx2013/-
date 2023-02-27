package class16;

public class GraphGenerator {

    public static Graph createGraph(int [][] matrix){

        Graph graph = new Graph();

        for (int i =0 ; i < matrix.length; i ++){

            //拿到每条边
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }

            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to ,new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
