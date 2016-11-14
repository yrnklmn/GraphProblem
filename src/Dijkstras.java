import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstras {

    public static void main(String[] args) {
//        DGraph g = new DGraph();
//        g.addVertex('A', Arrays.asList(new Vertex('B', 7), new Vertex('C', 8)));
//        g.addVertex('B', Arrays.asList(new Vertex('A', 7), new Vertex('F', 2)));
//        g.addVertex('C', Arrays.asList(new Vertex('A', 8), new Vertex('F', 6), new Vertex('G', 4)));
//        g.addVertex('D', Arrays.asList(new Vertex('F', 8)));
//        g.addVertex('E', Arrays.asList(new Vertex('H', 1)));
//        g.addVertex('F', Arrays.asList(new Vertex('B', 2), new Vertex('C', 6), new Vertex('D', 8), new Vertex('G', 9), new Vertex('H', 3)));
//        g.addVertex('G', Arrays.asList(new Vertex('C', 4), new Vertex('F', 9)));
//        g.addVertex('H', Arrays.asList(new Vertex('E', 1), new Vertex('F', 3)));

        Graph g = new Graph("D:\\Downloads\\Ex1\\G0.txt");
        System.out.println(g.getGraph().getShortestPath(0.0, 3.0));
    }

}

class Vertex implements Comparable<Vertex> {

    private Character id;
    private Double distance;
    private Double d_id;

    public Vertex(Double id, Double distance) {
        super();
        this.d_id = id;
        this.id = new Character((char) (48 + id));
        this.distance = distance;
    }

    public Double getId() {
        return d_id;
    }

    public double getDistance() {
        return distance;
    }

    public void setId(Character id) {
        this.id = id;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (distance == null) {
            if (other.distance != 0)
                return false;
        } else if (!distance.equals(other.distance))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vertex [id=" + id + ", distance=" + distance + "]";
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.distance < o.distance)
            return -1;
        else if (this.distance > o.distance)
            return 1;
        else
            return this.getId().compareTo(o.getId());
    }

}

class DGraph {

    private final Map<Double, List<Vertex>> vertices;

    public DGraph() {
        this.vertices = new HashMap<Double, List<Vertex>>();
    }

    public void addVertex(Double character, List<Vertex> vertex) {
        this.vertices.put(character, vertex);
    }

    public List<Double> getShortestPath(Double start, Double finish) {
        final Map<Double, Double> distances = new HashMap<Double, Double>();
        final Map<Double, Vertex> previous = new HashMap<Double, Vertex>();
        PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();

        for (Double vertex : vertices.keySet()) {
            if (vertex == start) {
                distances.put(vertex, 0.0);
                nodes.add(new Vertex(vertex, 0.0));
            } else {
                distances.put(vertex, Double.MAX_VALUE);
                nodes.add(new Vertex(vertex, Double.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            Vertex smallest = nodes.poll();
            if (smallest.getId() == finish) {
                final List<Double> path = new ArrayList<Double>();
                while (previous.get(smallest.getId()) != null) {
                    path.add(smallest.getId());
                    smallest = previous.get(smallest.getId());
                }
                return path;
            }

            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }

            for (Vertex neighbor : vertices.get(smallest.getId())) {
                Double alt = distances.get(smallest.getId()) + neighbor.getDistance();
                if (alt < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alt);
                    previous.put(neighbor.getId(), smallest);

                    forloop:
                    for (Vertex n : nodes) {
                        if (n.getId() == neighbor.getId()) {
                            nodes.remove(n);
                            n.setDistance(alt);
                            nodes.add(n);
                            break forloop;
                        }
                    }
                }
            }
        }

        return new ArrayList<Double>(distances.keySet());
    }

}
