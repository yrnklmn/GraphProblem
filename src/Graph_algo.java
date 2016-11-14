import java.util.List;

public class Graph_algo
{
    private String path;
    private Dijkstra.DijkstraSP dsp;

    public Graph_algo(String path) {
        this.path = path;
        dsp = new Dijkstra.DijkstraSP();

    }

    public double calcDistBetweenTwoVertex(int vertexA, int vertexB)
    {
        dsp.computePaths(dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = dsp.getVertex(vertexB);

        return vertex.minDistance;
    }
    public String calcBestPath(int vertexA, int vertexB)
    {
        String ans ="";
        dsp.computePaths(dsp.getVertex(vertexA));
        List<Dijkstra.Vertex> path = dsp.getShortestPathTo(dsp.vertices[vertexB]);

        return path.toString();

    }


    public static void main(String[] args)
    {
        Graph_algo graph_algo  = new Graph_algo("sdsds");
        System.out.println("calcDistBetweenTwoVertex: "+graph_algo.calcDistBetweenTwoVertex(5,1));
        System.out.println("calcBestPath: "+graph_algo.calcBestPath(2,1));
    }
}