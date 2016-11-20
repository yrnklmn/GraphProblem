package Graph;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class manage the extraction of a graph from a txt file
 */
public class Graph {
    private String path;
    private int num_of_nodes;
    private int num_of_edges;
    private Dijkstra.Vertex vertices[];
    protected Dijkstra.DijkstraSP dsp;
    private Vector<String> template;

    public Graph(String path) {
        this.path = path;
        template = new Vector<>();
        loadGraph();
    }

    /**
     * Copy constructor
     *
     * @param other
     */
    public Graph(Graph other) {
        this.path = other.path;
        this.num_of_nodes = other.getNumOfNodes();
        this.vertices = new Dijkstra.Vertex[num_of_nodes];
        for (int i = 0; i < num_of_nodes; i++) {
            this.vertices[i] = new Dijkstra.Vertex(other.vertices[i].name);
        }
        this.num_of_edges = other.num_of_edges;
        for (int i = 0; i < num_of_edges; i++) {
            StringTokenizer temp = new StringTokenizer(other.template.elementAt(i), " ");
            int FirstNode = Integer.parseInt((String) temp.nextElement());
            int SecondNode = Integer.parseInt((String) temp.nextElement());
            double weight = Double.parseDouble((String) temp.nextElement());
            Dijkstra.Edge edge1 = new Dijkstra.Edge(vertices[FirstNode], weight);
            Dijkstra.Edge edge2 = new Dijkstra.Edge(vertices[SecondNode], weight);
            vertices[FirstNode].adjacencies.add(edge2);
            vertices[SecondNode].adjacencies.add(edge1);
        }
        this.dsp = new Dijkstra.DijkstraSP(this.vertices);
    }

    /**
     * Open path's file and read data
     */
    private void loadGraph() {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = sc.nextInt();
            num_of_edges = sc.nextInt();
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
                template.add(FirstNode + " " + SecondNode + " " + weight);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dsp = new Dijkstra.DijkstraSP(vertices);
    }

    public int getNumOfNodes() {
        return this.num_of_nodes;
    }

    /**
     * Remove all Vertex connections from a given black list and assign it to the vertices array
     *
     * @param blackList
     */
    public void ShrinkGraph(int blackList[]) {
        removeAdjectives(blackList);
        removeVertices(blackList);
        this.num_of_nodes -= blackList.length;
    }

    /**
     * Managed by ShrinkGraph function, responsible for deleting the black listed Vertex from the graph
     *
     * @param blackList
     */
    private void removeVertices(int blackList[]) {
        Dijkstra.Vertex newVertices[] = new Dijkstra.Vertex[this.vertices.length - blackList.length];
        int j = 0;
//        for (int black : blackList) {
        for (int i = 0; i < vertices.length; i++) {
//                if (i != black) {
            if (!contain(i, blackList)) {
                newVertices[j++] = vertices[i];
            }
//        }
        }
//        }
        this.vertices = newVertices;
    }

    private boolean contain(int x, int[] list) {
        for (int y : list) {
            if (x == y) {
                return true;
            }
        }
        return false;
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
                // v.adjacencies.remove(current.name);
                for (int i = 0; i < v.adjacencies.size(); i++) {
                    if (v.adjacencies.get(i).vert.name == current.name) {
                        v.adjacencies.remove(i);
                    }
                }
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