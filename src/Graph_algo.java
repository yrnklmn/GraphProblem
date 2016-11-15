import java.util.List;

public class Graph_algo {
    private Dijkstra.DijkstraSP dsp;

    public Graph_algo(String path) {
        Graph graph = new Graph(path);
        dsp = new Dijkstra.DijkstraSP(graph.getVertecies());

    }

    public double calcDistBetweenTwoVertex(int vertexA, int vertexB) {

        dsp.computePaths(dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = dsp.getVertex(vertexB);

        return vertex.minDistance;
    }

    public String calcBestPath(int vertexA, int vertexB) {

        dsp.computePaths(dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = dsp.getVertex(vertexB);
        List<Dijkstra.Vertex> path = dsp.getShortestPathTo(vertex);

        return path.toString();

    }


    public static void main(String[] args) {
        Graph_algo graph_algo = new Graph_algo("exampleFiles\\G0.txt");
//        System.out.println("calcDistBetweenTwoVertex: " + graph_algo.calcDistBetweenTwoVertex(0, 1));
        System.out.println("calcBestPath: " + graph_algo.calcBestPath(0, 1));
    }
}