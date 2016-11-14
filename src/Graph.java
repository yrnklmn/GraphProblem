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
            for (int i = 0; i < num_of_nodes; i++) {
                vertices[i] = new Dijkstra.Vertex(i);
                vertices[i].adjacencies = new Dijkstra.Edge[0];
            }
            Stack<Dijkstra.Edge> neighbor[] = new Stack[num_of_nodes];
            for (int i = 0; i < num_of_nodes; i++) {
                neighbor[i] = new Stack<>();
            }
            int u = sc.nextInt();
            for (int i = 0; i < num_of_nodes; i++) {
                while (u == i) {
                    int v = sc.nextInt();
                    double w = sc.nextDouble();
                    neighbor[u].push(getEdge(u, v, w));
                    neighbor[v].push(getEdge(v, u, w));
                    if (sc.hasNext()) {
                        u = sc.nextInt();
                    } else {
                        break;
                    }
                }
            }
            setNeighbors(neighbor);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setNeighbors(Stack<Dijkstra.Edge> st[]) {

        for (int i = 0; i < vertices.length; i++) {
            vertices[i].adjacencies = new Dijkstra.Edge[st[i].size()];
            int j = 0;
            while (!st[i].isEmpty()) {
                vertices[i].adjacencies[j++] = st[i].pop();
            }
            j = 0;
        }
    }

    private Dijkstra.Edge getEdge(int u, int v, double w) {
        return new Dijkstra.Edge(vertices[v], w);
    }

    public static void main(String[] args) {
        String path = "exampleFiles\\G0.txt";
        Graph p = new Graph(path);
    }
}


