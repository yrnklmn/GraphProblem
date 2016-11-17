package QA;

import Graph.Graph;
import Graph.Graph_algo;

public class Sanity {
    private Graph graph;
    private TestFile testFile;

    public Sanity(String graphPath, String testPath) {
        this.graph = new Graph(graphPath);
        this.testFile = new TestFile(testPath);
    }

    public void printGraph() {
        System.out.println("Graph:");
        System.out.println(this.graph.toString());
    }

    public void printTestFile() {
        System.out.println("Test File:");
        System.out.println(this.testFile.toString());
    }

    public void startTest() {
        for (TestFile.Query q : testFile.getQueue()) {
            System.out.println(execute(q));
        }
    }

    private String execute(TestFile.Query q) {
        double cost = Graph_algo.shortestCost(this.graph, q.VertexA, q.VertexB);
        String path = Graph_algo.shortestPath(this.graph, q.VertexA, q.VertexB);
        String noBlackList = Graph_algo.shortestPathExcludeBlacklist(this.graph, q.VertexA, q.VertexB, q.blackList);
        return cost + "; " + path + "; " + noBlackList;
    }

    public static void main(String[] args) {
        String graphPath = "exampleFiles\\G0.txt";
        String testPath = "exampleFiles\\test1.txt";
        Sanity sanity = new Sanity(graphPath, testPath);
        sanity.printGraph();
        sanity.printTestFile();
        sanity.startTest();

    }
}
