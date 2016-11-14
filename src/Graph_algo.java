import java.util.List;


//TODO: ALL METHODS SHOULD BE STATIC,   ARGUMENTS SHOULD BE (Graph graph, int VertexA, int VertexB)


public class Graph_algo {
    private Dijkstra.DijkstraSP dsp;

    public Graph_algo(String path) {
        dsp = new Dijkstra.DijkstraSP((new Graph(path)).getVertecies());
    }

    public double shortestCost(int vertexA, int vertexB) {
        dsp.computePaths(dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = dsp.getVertex(vertexB);
        return vertex.minDistance;
    }

    public String shortestPath(int vertexA, int vertexB) {
        dsp.computePaths(dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = dsp.getVertex(vertexB);
        List<Dijkstra.Vertex> path = dsp.getShortestPathTo(vertex);
        return path.toString();
    }

//    public String shortestPathExcludeBlacklist(int vertexA, int vertexB, List blackList) {
//
//    }

    public static void main(String[] args) {
        Graph_algo graph_algo = new Graph_algo("exampleFiles\\G0.txt");
        System.out.println("shortestCost: " + graph_algo.shortestCost(0, 1));
        System.out.println("shortestPath: " + graph_algo.shortestPath(0, 1));
    }
}