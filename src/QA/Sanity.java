package QA;

import Graph.Graph;
import Graph.Graph_algo;

/**
 * This class responsible for sanity checking the project
 */
public class Sanity {
    private Graph main_graph;
    private Graph temp_graph;
    private TestFile testFile;

    public Sanity(String graphPath, String testPath) {
        this.main_graph = new Graph(graphPath);
        this.testFile = new TestFile(testPath);
    }

    /**
     * Printing the current main_graph
     */
    public void printGraph() {
        System.out.println("Graph:");
        System.out.println(this.main_graph.toString());
    }

    /**
     * Printing the current Test File
     */
    public void printTestFile() {
        System.out.println("Test File:");
        System.out.println(this.testFile.toString());
    }

    /**
     * Starting the test that was received from TestFile on the current main_graph
     */
    public void startTest() {
        for (TestFile.Query q : testFile.getQueue()) {
            System.out.println("Test: " + q.toString());
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
        double cost;
        String path;
        String pathWithNoBlackList;
        temp_graph = new Graph(main_graph);
        if (q.blackList.length > 0) {
            temp_graph.ShrinkGraph(q.blackList);
            cost = Graph_algo.shortestCost(this.temp_graph, q.VertexA, q.VertexB);
            pathWithNoBlackList = Graph_algo.shortestPathExcludeBlacklist(this.temp_graph, q.VertexA, q.VertexB);
            return "Cost: " + cost + ";\t" + pathWithNoBlackList;
        } else {
            cost = Graph_algo.shortestCost(this.temp_graph, q.VertexA, q.VertexB);
            path = Graph_algo.shortestPath(this.temp_graph, q.VertexA, q.VertexB);
            return "Cost: " + cost + ";\t" + path;
        }
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
