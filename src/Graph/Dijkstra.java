package Graph;

import java.util.Vector;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dijkstra {

    public static class DijkstraSP {
        Vertex[] vertices;

        public DijkstraSP(Vertex[] vertices) {
            this.vertices = vertices;
        }

        public void computePaths(Vertex s) {
            s.minDistance = 0.;
            PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
            vertexQueue.add(s);
            while (!vertexQueue.isEmpty()) {
                Vertex u = vertexQueue.poll();
                for (Edge e : u.adjacencies) {
                    Vertex v = e.vert;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }

        @Override
        public String toString() {
            String res = "";
            for (int i = 0; i < vertices.length; i++) {
                res += vertices[i].name + " : \n" + Arrays.toString(vertices[i].adjacencies.toArray()) + "\n";
            }
            return res;
        }

        public Vertex getVertex(int index) {
            return vertices[index];
        }

        public List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> path = new ArrayList<Vertex>();
            for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
                path.add(vertex);
            Collections.reverse(path);
            return path;
        }
    }

    static class Vertex implements Comparable<Vertex> {
        public int name;
        public Vector<Edge> adjacencies = new Vector<Edge>();
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public Vertex(int argName) {
            name = argName;
        }

        public String toString() {
//            return "<" + name + ";" + this.adjacencies.toString() + ">";
            return this.name + "";
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

        public String toString() {
            return "(" + vert.name + "," + weight + ")";
        }
    }
}