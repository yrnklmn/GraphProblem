import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {

    private int num_of_nodes, num_of_edges;
    private Vertex weights[];

    public Graph(String GraphFile) {
        loadGraph(GraphFile);
    }

    private void print() {
        for (int i = 0; i < weights.length; i++) {
            System.out.println(weights[i]);
        }
    }

    private void loadGraph(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            num_of_nodes = (int) sc.nextDouble();
            num_of_edges = (int) sc.nextDouble();
            weights = new Vertex[num_of_edges];
            for (int i = 0; i < num_of_edges; i++) {
                weights[i] = new Vertex(((int) sc.nextDouble()), ((int) sc.nextDouble()), sc.nextDouble());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "D:\\Downloads\\Ex1\\G0.txt";
        Graph g = new Graph(path);
    }

    class Vertex {
        private int v, u;
        private double weight;

        public Vertex(int v, int u, double weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + v + "," + u + "," + weight + ")";
        }
    }
}
