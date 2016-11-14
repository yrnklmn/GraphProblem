package Yehuda;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Graph
{
    private String path;
    private int num_of_nodes;
    private int num_of_edges;
    private Dijkstra.Vertex vertices [];
    private Dijkstra.DijkstraSP dsp;

    public Graph(String path)
    {
        this.path = path;


        try {
            Scanner sc = new Scanner(new File(path));

            num_of_nodes = (int) sc.nextDouble();
            num_of_edges = (int) sc.nextDouble();

            System.out.println("num_of_nodes: "+num_of_nodes);
            System.out.println("num_of_edges: "+num_of_edges);

            vertices = new Dijkstra.Vertex[num_of_nodes];
            for(int i=0; i<vertices.length; i++)
            {
                vertices[i]=new Dijkstra.Vertex(i);
            }
            while(sc.hasNext()==true)
            {
                int FirstNode =sc.nextInt();
                int SecondNode = sc.nextInt();
                double edge = sc.nextDouble();


                Dijkstra.Edge edge1 = new Dijkstra.Edge(vertices[FirstNode],edge);
                Dijkstra.Edge edge2 = new Dijkstra.Edge(vertices[SecondNode],edge);

                vertices[FirstNode].adjacencies.add(edge1);
                vertices[SecondNode].adjacencies.add(edge2);

            }







        } catch (Exception e){
            e.printStackTrace();
        }

        dsp = new Dijkstra.DijkstraSP(vertices);
        dsp.print();


//        System.out.println(Arrays.toString(dsp.vertices[0].adjacencies.toArray()));


//            Vertex v0 = new Vertex(0);
//            Vertex v1 = new Vertex(1);
//            Vertex v2 = new Vertex(2);
//            Vertex v3 = new Vertex(3);
//            Vertex v4 = new Vertex(4);
//            Vertex v5 = new Vertex(5);
//
//            v0.adjacencies = new Edge[]{new Edge(v3, 4.1), new Edge(v4, 1.1), new Edge(v5, 3)};
//            v1.adjacencies = new Edge[]{new Edge(v2, 3), new Edge(v4, 4.2)};
//            v2.adjacencies = new Edge[]{new Edge(v1, 3), new Edge(v3, 5.16), new Edge(v4, 2.2)};
//            v3.adjacencies = new Edge[]{new Edge(v0, 4.1), new Edge(v2, 5.16), new Edge(v5, 0.3)};
//            v4.adjacencies = new Edge[]{new Edge(v0, 1.1), new Edge(v1, 4.2), new Edge(v2, 2.2), new Edge(v5, 2.2)};
//            v5.adjacencies = new Edge[]{new Edge(v0, 3), new Edge(v3, 0.3), new Edge(v4, 2.2)};
//            Vertex[] vertices = {v0, v1, v2, v3, v4, v5};
//            return vertices;


    }


    public static void main(String[] args)
    {
        Graph graph = new Graph("exampleFiles//g0.txt");

        System.out.println("test");

    }
}
