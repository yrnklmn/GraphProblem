package Graph;

import java.util.List;

/**
 * This class manage the actual algorithm calculations
 */
public class Graph_algo {
    /**
     * Calculate the shortest cost from VertexA to VertexB on graph
     *
     * @param graph
     * @param vertexA
     * @param vertexB
     * @return value of minimum distance between two vertices
     */
    public static double shortestCost(Graph graph, int vertexA, int vertexB) {
        graph.dsp.computePaths(graph.dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = graph.dsp.getVertex(vertexB);
        return vertex.minDistance;
    }

    /**
     * Calculate the shortest path from VertexA to VertexB on graph
     *
     * @param graph
     * @param vertexA
     * @param vertexB
     * @return string the represents the shortest path contains vertices on the path
     */
    public static String shortestPath(Graph graph, int vertexA, int vertexB) {
        graph.dsp.computePaths(graph.dsp.getVertex(vertexA));
        Dijkstra.Vertex vertex = graph.dsp.getVertex(vertexB);
        List<Dijkstra.Vertex> path = graph.dsp.getShortestPathTo(vertex);
        return path.toString();
    }

    /**
     * Calculate the shortest path from VertexA to VertexB on graph while ignoring the blacklist vertices
     *
     * @param graph
     * @param vertexA
     * @param vertexB
     * @param blackList
     * @return string the represents the shortest path contains vertices on the path
     */
    public static String shortestPathExcludeBlacklist(Graph graph, int vertexA, int vertexB, int blackList[]) {
//TODO: complete this function
        graph.dsp.computePaths(graph.dsp.getVertex(vertexA), blackList);
        Dijkstra.Vertex vertex = graph.dsp.getVertex(vertexB);
        List<Dijkstra.Vertex> path = graph.dsp.getShortestPathTo(vertex);
        return path.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph("exampleFiles\\G0.txt");
        System.out.println("shortestCost: " + shortestCost(graph, 0, 1));
        System.out.println("shortestPath: " + shortestPath(graph, 0, 1));
        System.out.println("shortestPathWithBlackList: " + shortestPathExcludeBlacklist(graph, 0, 1, new int[]{}));
    }
}