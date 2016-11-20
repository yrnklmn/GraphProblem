package QA;

import Graph.Graph;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class manage the extraction of a test queries from a txt file
 */
public class TestFile {

    private int num_of_query;
    private Query[] testQueue;
    private String path;

    public TestFile(String path) {
        this.path = path;
        loadTestFile();
    }

    /**
     * Open path's file and read data
     */
    private void loadTestFile() {
        try {
            Scanner sc = new Scanner(new File(path));
            this.num_of_query = (sc.nextInt()) - 1;
            this.testQueue = new Query[num_of_query];
            for (int i = 0; i < num_of_query; i++) {
                int VertexA = sc.nextInt();
                int VertexB = sc.nextInt();
                int BL_size = sc.nextInt();
                if (BL_size > 0) {
                    int blackList[] = new int[BL_size];
                    for (int j = 0; j < BL_size; j++) {
                        blackList[j] = sc.nextInt();
                    }
                    testQueue[i] = new Query(VertexA, VertexB, blackList);
                } else {
                    testQueue[i] = new Query(VertexA, VertexB, new int[]{});
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the filled test query arrays
     */
    public Query[] getQueue() {
        return this.testQueue;
    }

    @Override
    public String toString() {
        String res = "";
        for (Query q : testQueue) {
            res += q.toString() + ",\n";
        }
        return res;
    }

    /**
     * This class represent a single query from Test File
     */
    static class Query {
        int VertexA, VertexB;
        int blackList[];

        public Query(int VertexA, int VertexB, int[] blackList) {
            this.blackList = blackList;
            this.VertexA = VertexA;
            this.VertexB = VertexB;
        }

        @Override
        public String toString() {
            return VertexA + " " + VertexB + " " + blackList.length + " " + Arrays.toString(blackList);
        }
    }

    public static void exportResults(Graph graph, Sanity.Result[] results, double runtime) {
        try {
            File output = new File("\\result.txt");
            PrintWriter pr = new PrintWriter(output);
            output.createNewFile();
            for (Sanity.Result r : results) {
                pr.print(r.getWeight());
            }
            pr.print("Graph: |V|=" + graph.getNumOfNodes() + ", |E|=" + graph.getNumOfEdges() + "!TIE, Radius:"
                    + graph.getRadius() + ", Diameter: " + graph.getDiameter() + ", runtime: " + runtime);
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
