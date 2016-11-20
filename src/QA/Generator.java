package QA;

import java.io.File;
import java.io.PrintWriter;

public class Generator {
    private File output;
    private PrintWriter stream;

    public Generator(int fileNumber) {
        this.output = new File("exampleFiles\\G" + fileNumber + ".txt");
        try {
            this.output.createNewFile();
            this.stream = new PrintWriter(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getLines(int x) {
        int lines = 0;
        for (int i = 0; i < x; i++) {
            lines+=i;
        }
        return lines;
    }

    public void generateGraph(int numOfVertex) {
        stream.println(numOfVertex);
        stream.println(getLines(numOfVertex));
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = i + 1; j < numOfVertex; j++) {
                if(i==numOfVertex-1 && j==numOfVertex-1){
                    stream.print(i + " " + j + " " + ((Math.random() * 60) + 30));
                }else {
                    stream.println(i + " " + j + " " + ((Math.random() * 60) + 30));
                }
            }
        }
        stream.close();
    }

    public static void main(String args[]) {
        // number of file ( G<x>.txt )
        int fileNumber = 9;
        Generator g = new Generator(fileNumber);
        // number of nodes in graph
        int nodes = 500;
        g.generateGraph(nodes);
    }
}