package Graph;

import java.io.File;
import java.util.Scanner;

public class Graph {
    private String path;
    private int num_of_nodes;
    private Dijkstra.Vertex vertices[];
    protected Dijkstra.DijkstraSP dsp;

    public Graph(String path) {
        this.path = path;
        loadGraph();
    }

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

    public Dijkstra.Vertex[] getVertices() {
        return this.vertices;
    }

    public void ShrinkGraph(int blackList[]) {
        removeAdjectives(blackList);
        removeVertices(blackList);
    }

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