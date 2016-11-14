package Yehuda;

import java.util.*;

public class Dijkstra {


    static class Vertex implements Comparable<Vertex> {
        public  int name;
        public Vector<Edge>  adjacencies  = new Vector<Edge>();
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;


        public Vertex(int argName) {
            name = argName;
        }

        public String toString() {
            return name + "";
        }

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }
    }

    static class Edge {
        public final Vertex vert;
        public final double weight;

        public Edge(Vertex v, double w) {
            vert = v;
            weight = w;
        }
        public String toString()
        {
            return "["+vert+","+weight+"]";
        }
    }

    public static class DijkstraSP {
        Vertex[] vertices;

//        public Vertex[] init1() {
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
//        }

//        public DijkstraSP() {
//            vertices = init1();
//        }
    public DijkstraSP(Vertex [] vertices)
    {
        this.vertices = vertices;
    }

        public Vertex getVertex(int index) {

            return vertices[index];
        }

        public void computePaths(Vertex s) {
            s.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(s);
            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();
                // Visit each edge exiting u
                for (Edge e : u.adjacencies) {
                    Vertex v = e.vert;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {//relaxation
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }
        public void print() {
            for (int i = 0; i < vertices.length; i++) {
                System.out.print(vertices[i].name + " : ");
                System.out.println(Arrays.toString(vertices[i].adjacencies.toArray()));
            }
        }

        public void printPathes() {
            for (Vertex v : vertices) {
                System.out.println("Distance to " + v + ": " + v.minDistance);
                List<Vertex> path = getShortestPathTo(v);
                System.out.println("Path: " + path);
            }
        }

        public List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);
            Collections.reverse(path);
            return path;
        }
    }

    public static void main(String[] args) {
//        String path = "exampleFiles\\G0.txt";
//
//        Graph graph = new Graph(path);

       // DijkstraSP dsp = new DijkstraSP(new Graph("exampleFiles\\G0.txt"));
//        dsp.computePaths(dsp.getVertex(2));
//        //dsp.printPathes();
//        System.out.println(dsp.getShortestPathTo(dsp.getVertex(5)));
       //dsp.printPathes();
    }
}
