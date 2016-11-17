package Graph;

import java.io.File;
import java.util.Scanner;

/**
 * This class manage the extraction of a graph from a txt file
 */
public class Graph {
    private String path;
    private int num_of_nodes;
    private Dijkstra.Vertex vertices[];
    protected Dijkstra.DijkstraSP dsp;

    public Graph(String path) {
        this.path = path;
        loadGraph();
    }

    /**
     * Open path's file and read data
     */
    private void loadGraph() {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = sc.nextInt();
            sc.nextInt();
            vertices = new Dijkstra.Vertex[num_of_nodes];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Dijkstra.Vertex(i);
            }
            while (sc.hasNext() == true) {
                int FirstNode = sc.nextInt();
                int SecondNode = sc.nextInt();
                double weight = sc.nextDouble();
                Dijkstra.Edge edge1 = new Dijkstra.Edge(vertices[FirstNode], weight);
                Dijkstra.Edge edge2 = new Dijkstra.Edge(vertices[SecondNode], weight);
                vertices[FirstNode].adjacencies.add(edge2);
                vertices[SecondNode].adjacencies.add(edge1);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dsp = new Dijkstra.DijkstraSP(vertices);
    }

    /**
     * @return Array of Vertices represents the graph
     */
    public Dijkstra.Vertex[] getVertices() {
        return this.vertices;
    }

    /**
     * Remove all Vertex connections from a given black list and assign it to the vertices array
     *
     * @param blackList
     */
    public void ShrinkGraph(int blackList[]) {
        removeAdjectives(blackList);
        removeVertices(blackList);
    }

    /**
     * Managed by ShrinkGraph function, responsible for deleting the black listed Vertex from the graph
     *
     * @param blackList
     */
    private void removeVertices(int blackList[]) {
        Dijkstra.Vertex newVertices[] = new Dijkstra.Vertex[this.vertices.length - blackList.length];
        int j = 0;
        for (int black : blackList) {
            for (int i = 0; i < vertices.length; i++) {
                if (i != black) {
                    newVertices[j++] = vertices[i];
                }
            }
        }
        this.vertices = newVertices;
    }

    /**
     * Managed by ShrinkGraph function, responsible for deleting the black listed vertices connections
     * from other vertices in the graph
     *
     * @param blackList
     */
    private void removeAdjectives(int blackList[]) {
        for (int black : blackList) {
            Dijkstra.Vertex current = vertices[black];
            for (Dijkstra.Edge e : current.adjacencies) {
                Dijkstra.Vertex v = e.vert;
                v.adjacencies.remove(current.name);
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (Dijkstra.Vertex v : vertices) {
            res += v.toString() + ",\n";
        }
        return res;
    }
}