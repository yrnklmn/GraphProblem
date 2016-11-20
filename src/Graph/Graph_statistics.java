package Graph;

public class Graph_statistics
{
    private Graph main_graph;
    double  [][] D;
    int n,vertexdiameterA,vertexdiameterB,vertexRadiusA, vertexRadiusB =0;
    double diameterWeight = 0,RadiusWwight = infinity;
    private static int infinity = Integer.MAX_VALUE;

    public Graph_statistics(Graph graph)
    {
        this.main_graph = graph;
        convetGraphToMatrix();  //init elements to D matrix.
        FWAlgorithm();
        Diameter();

    }
    private void convetGraphToMatrix()
    {
        this.n = main_graph.getNumOfNodes();
        this.D = new double[n][n];

        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                if(i==j)
                {
                    D[i][j]=0;
                }
                else
                {
                    D[i][j]= infinity;
                }
            }
        }

        for (int i=0; i<n; i++)
        {
            int numOfadjacencies = main_graph.dsp.getVertex(i).adjacencies.size();
            for(int j=0; j<numOfadjacencies; j++)
            {
                int vert = main_graph.dsp.getVertex(i).adjacencies.get(j).vert.name;
                double weight = main_graph.dsp.getVertex(i).adjacencies.get(j).weight;
                D[i][vert] = weight;
            }
        }
    }

    public void FWAlgorithm()
    {
        for (int k = 0; k<n; k++){
            for (int i = 0; i<n; i++){
                for (int j = 0; j<n; j++){
                    if (D[i][k]!=infinity && D[k][j]!=infinity){
                        if (D[i][j] > D[i][k]+D[k][j]){
                            D[i][j] = D[i][k]+D[k][j];
                        }
                    }
                }
            }
        }
    }

    public void PrintMatrix()
    {
        System.out.println("diameterWeight= "+diameterWeight);
        System.out.println("vertexA= "+vertexdiameterA);
        System.out.println("vertexB= "+vertexdiameterB);
        System.out.println("RadiusWwight= "+RadiusWwight);
        System.out.println("vertexRadiusA= "+vertexRadiusA);
        System.out.println("vertexRadiusB= "+vertexRadiusB);

        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                System.out.print(D[i][j] + " , ");
            }
            System.out.println();
        }
    }

    private void Diameter()
    /**
     * Calculate the  Diameter,
     * return the weith of the Diameter and the tw0 vertexes.
     */
    {
           for (int i =0; i<n; i++)
           {
               for (int j=0; j<n; j++)
               {
                    if(D[i][j] > diameterWeight && D[i][j]!=0)
                    {
                        diameterWeight = D[i][j];
                        vertexdiameterA = i;
                        vertexdiameterB = j;
                    }
                    if(D[i][j]<RadiusWwight && D[i][j]!=0)
                    {
                        RadiusWwight = D[i][j];
                        vertexRadiusA = i;
                        vertexRadiusB = j;
                    }
               }
           }
    }


    public static void main(String[] args)
    {
        Graph graph = new Graph("exampleFiles\\G0.txt");
        System.out.println(graph.toString());
        Graph_statistics graph_statistics  =new Graph_statistics(graph);
        graph_statistics.PrintMatrix();

    }

}
