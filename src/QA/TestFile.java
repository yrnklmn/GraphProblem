package QA;

import java.io.File;
import java.util.Scanner;

public class TestFile {

    private int num_of_query;
    private Query[] testQueue;
    private String path;

    public TestFile(String path) {
        this.path = path;
        loadTestFile();
    }

    private void loadTestFile() {
        try {
            Scanner sc = new Scanner(new File(path));
            this.num_of_query = sc.nextInt();
            this.testQueue = new Query[num_of_query];
            for (int i = 0; i < num_of_query; i++) {
                int VertexA = sc.nextInt();
                int VertexB = sc.nextInt();
                int BL_size = sc.nextInt();
                int blackList[] = new int[BL_size];
                for (int j = 0; j < BL_size; j++) {
                    blackList[j] = sc.nextInt();
                }
                testQueue[i] = new Query(VertexA, VertexB, blackList);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class Query {
        int VertexA, VertexB;
        int blackList[];

        public Query(int VertexA, int VertexB, int[] blackList) {
            this.blackList = blackList;
            this.VertexA = VertexA;
            this.VertexB = VertexB;
        }
    }
}
