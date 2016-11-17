
// Or's Version Of Graph.Graph class
/**
 * import java.io.File;
 * import java.io.FileNotFoundException;
 * import java.util.List;
 * import java.util.Scanner;
 * import java.util.Stack;
 * import java.util.Vector;
 * <p>
 * public class Graph.Graph {
 * <p>
 * private int num_of_nodes, num_of_edges;
 * private Graph.Dijkstra.Vertex[] vertices;
 * <p>
 * public Graph.Graph(String GraphFile) {
 * loadGraph(GraphFile);
 * }
 * <p>
 * public Graph.Dijkstra.Vertex[] getVertices() {
 * return this.vertices;
 * }
 * <p>
 * private void loadGraph(String path) {
 * try {
 * Scanner sc = new Scanner(new File(path));
 * num_of_nodes = (int) sc.nextDouble();
 * num_of_edges = (int) sc.nextDouble();
 * this.vertices = new Graph.Dijkstra.Vertex[num_of_nodes];
 * for (int i = 0; i < num_of_nodes; i++) {
 * vertices[i] = new Graph.Dijkstra.Vertex(i);
 * vertices[i].adjacencies = new Graph.Dijkstra.Edge[0];
 * }
 * Stack<Graph.Dijkstra.Edge> neighbor[] = new Stack[num_of_nodes];
 * for (int i = 0; i < num_of_nodes; i++) {
 * neighbor[i] = new Stack<>();
 * }
 * int u = sc.nextInt();
 * for (int i = 0; i < num_of_nodes; i++) {
 * while (u == i) {
 * int v = sc.nextInt();
 * double w = sc.nextDouble();
 * neighbor[u].push(getEdge(u, v, w));
 * neighbor[v].push(getEdge(v, u, w));
 * if (sc.hasNext()) {
 * u = sc.nextInt();
 * } else {
 * break;
 * }
 * }
 * }
 * setNeighbors(neighbor);
 * sc.close();
 * } catch (FileNotFoundException e) {
 * e.printStackTrace();
 * }
 * }
 * <p>
 * private void setNeighbors(Stack<Graph.Dijkstra.Edge> st[]) {
 * <p>
 * for (int i = 0; i < vertices.length; i++) {
 * vertices[i].adjacencies = new Graph.Dijkstra.Edge[st[i].size()];
 * int j = 0;
 * while (!st[i].isEmpty()) {
 * vertices[i].adjacencies[j++] = st[i].pop();
 * }
 * j = 0;
 * }
 * }
 * <p>
 * private Graph.Dijkstra.Edge getEdge(int u, int v, double w) {
 * return new Graph.Dijkstra.Edge(vertices[v], w);
 * }
 * <p>
 * public static void main(String[] args) {
 * String path = "exampleFiles\\G0.txt";
 * Graph.Graph p = new Graph.Graph(path);
 * }
 * }
 */