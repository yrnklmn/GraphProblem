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
    private double runTime, startTime, endTime;
    private Result results[];

    public Sanity(String graphPath, String testPath) {
        startTime = System.currentTimeMillis();
        this.main_graph = new Graph(graphPath);
        this.testFile = new TestFile(testPath);
    }

    /**
     * Starting the test that was received from TestFile on the current main_graph
     */
    public Result[] startTest() {
        this.results = new Result[testFile.getQueue().length];
        for (int i = 0; i < testFile.getQueue().length; i++) {
            results[i] = execute(testFile.getQueue()[i]);
        }
        endTime = System.currentTimeMillis();
        runTime = endTime - startTime;
        return results;
    }

    public double getRunTime() {
        return this.runTime;
    }

    /**
     * execute each test query from the Test File
     *
     * @param q
     * @return results for each query is format: cost + "; " + path + "; " + noBlackList
     */
    private Result execute(TestFile.Query q) {
        double cost;
        String path;
        temp_graph = new Graph(main_graph);
        if (q.blackList.length > 0) {
            temp_graph.ShrinkGraph(q.blackList);
            cost = Graph_algo.shortestCost(this.temp_graph, q.VertexA, q.VertexB);
            path = Graph_algo.shortestPathExcludeBlacklist(this.temp_graph, q.VertexA, q.VertexB);
        } else {
            cost = Graph_algo.shortestCost(this.temp_graph, q.VertexA, q.VertexB);
            path = Graph_algo.shortestPath(this.temp_graph, q.VertexA, q.VertexB);
        }
        return new Result(q, path, cost);
    }

    class Result {
        private TestFile.Query query;
        private double weight;
        private String path;

        public Result(TestFile.Query query, String path, double weight) {
            this.weight = weight;
            this.path = path;
            this.query = query;
        }

        public TestFile.Query getQuery() {
            return this.query;
        }

        public String getPath() {
            return this.path;
        }

        public double getWeight() {
            return this.weight;
        }

        @Override
        public String toString() {
            return this.weight + " " + this.path;
        }
    }

    public static void main(String[] args) {
        String graphPath = "exampleFiles\\G0.txt";
        String testPath = "exampleFiles\\test1.txt";
        Sanity sanity = new Sanity(graphPath, testPath);
        Result[] results = sanity.startTest();
        TestFile.exportResults(sanity.main_graph, results, sanity.getRunTime());
    }
}
