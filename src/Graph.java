import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Graph {

    private int num_of_nodes, num_of_edges;
    private DGraph g;

    public Graph(String GraphFile) {
        loadGraph(GraphFile);
    }

    private void loadGraph(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = (int) sc.nextDouble();
            num_of_edges = (int) sc.nextDouble();
            g = new DGraph();
            Double u = new Double(0);
            Double v = new Double(0);
            Double w = new Double(0);
            List<Vertex> vec[] = new Vector[num_of_edges];
            for (int i = 0; i < num_of_edges; i++) {
                vec[i] = new Vector<>();
            }
            u = sc.nextDouble();
            for (int i = 0; i < num_of_nodes; i++) {
                while (u == i) {
                    v = sc.nextDouble();
                    w = sc.nextDouble();
                    vec[u.intValue()].add(new Vertex(v, w));
                    vec[v.intValue()].add(new Vertex(u, w));
                    if (sc.hasNext()) {
                        u = sc.nextDouble();
                    } else {
                        break;
                    }
                }
                g.addVertex(i + 0.0, vec[i]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DGraph getGraph() {
        return this.g;
    }

    public static void main(String[] args) {
        String path = "D:\\Downloads\\Ex1\\G0.txt";
        Graph g = new Graph(path);
    }
}

class myClass {
}

