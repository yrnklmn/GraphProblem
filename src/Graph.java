import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Graph {

    private int num_of_nodes, num_of_edges;
    private Dijkstra.Vertex[] vertices;

    public Graph(String GraphFile) {
        loadGraph(GraphFile);
    }

    public Dijkstra.Vertex[] getVertecies() {
        return this.vertices;
    }

    private void loadGraph(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = (int) sc.nextDouble();
            num_of_edges = (int) sc.nextDouble();
            this.vertices = new Dijkstra.Vertex[num_of_nodes];
            Vector<Dijkstra.Edge> neighbor = new Vector();
            for (int i = 0; i < num_of_nodes; i++) {
                Dijkstra.Vertex v = new Dijkstra.Vertex(sc.nextInt());
                while (v.name == i) {
                    neighbor.add(new Dijkstra.Edge(new Dijkstra.Vertex(sc.nextInt()), sc.nextDouble()));
                }
                v.adjacencies = new Dijkstra.Edge((Dijkstra.Edge[]) neighbor.toArray());
//                vertices[i] =
//                Dijkstra.Vertex v = new Dijkstra.Vertex(sc.nextInt());
//                st.push(new Dijkstra.Edge(v, sc.nextDouble()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "exampleFiles\\G0.txt";
    }
}


