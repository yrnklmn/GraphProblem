package QA;

import Graph.Graph;
import Graph.Graph_algo;

/**
 * This class responsible for sanity checking the project
 */
public class Sanity {
    private Graph graph;
    private TestFile testFile;

    public Sanity(String graphPath, String testPath) {
        this.graph = new Graph(graphPath);
        this.testFile = new TestFile(testPath);
    }

    /**
     * Printing the current graph
     */
    public void printGraph() {
        System.out.println("Graph:");
        System.out.println(this.graph.toString());
    }

    /**
     * Printing the current Test File
     */
    public void printTestFile() {
        System.out.println("Test File:");
        System.out.println(this.testFile.toString());
    }

    /**
     * Starting the test that was received from TestFile on the current graph
     */
    public void startTest() {
        for (TestFile.Query q : testFile.getQueue()) {
            System.out.println(execute(q));
        }
    }

    /**
     * execute each test query from the Test File
     *
     * @param q
     * @return results for each query is format: cost + "; " + path + "; " + noBlackList
     */
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
