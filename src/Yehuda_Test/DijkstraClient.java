package Yehuda_Test;


/**
 * Client code to demonstrate Dijkstra's algorithm
 * 
 * @author Stewart Wright
 *
 */
public class DijkstraClient {
	
	public static void main(String[] args) {
	
		// Define the edges of the graph
	
		Edge[] edges =
			{	new Edge(0,3,4.1),
				new Edge(0,4,1.1),
				new Edge(0,5,3),
				new Edge(1,2,3),
				new Edge(1,4,4.2),
				new Edge(2,3,5.16),
				new Edge(2,4,4.2),
				new Edge(2,3,5.16),
				new Edge(2,4,2.2),
				new Edge(3,5,5.16),
				new Edge(2,4,2.2),
				new Edge(3,5,0.3),
				new Edge(4,5,2.2)
			};
		
		// Create the graph
		
		Graph graph = new Graph(edges);
		
		// Update the graph with the shortest distances
		
		graph.calculateShortestDistances();
		
		// Display the graph
		
		System.out.println(graph.toString());
	
	}

}