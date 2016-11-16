package Graph;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Graph {
    private String path;
    private int num_of_nodes;
    private int num_of_edges;
    private Dijkstra.Vertex vertices[];
    protected Dijkstra.DijkstraSP dsp;

    public Graph(String path) {
        this.path = path;
        loadGraph();
    }

    private void loadGraph() {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = (int) sc.nextDouble();
            num_of_edges = (int) sc.nextDouble();
            vertices = new Dijkstra.Vertex[num_of_nodes];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Dijkstra.Vertex(i);
            }
            while (sc.hasNext() == true) {
                int FirstNode = sc.nextInt();
                int SecondNode = sc.nextInt();
                double edge = sc.nextDouble();
                Dijkstra.Edge edge1 = new Dijkstra.Edge(vertices[FirstNode], edge);
                Dijkstra.Edge edge2 = new Dijkstra.Edge(vertices[SecondNode], edge);
                vertices[FirstNode].adjacencies.add(edge1);
                vertices[SecondNode].adjacencies.add(edge2);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dsp = new Dijkstra.DijkstraSP(vertices);
    }

    public Dijkstra.Vertex[] getVertecies() {
        return this.vertices;
    }

    public void ShrinkGraph(List<Dijkstra.Vertex> blackList) {

    }
}