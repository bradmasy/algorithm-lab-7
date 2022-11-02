import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    int[][] adjacencyMatrix;
    boolean directed;

    public Graph(final int v){
        adjacencyMatrix = new int[v][v];
        directed = false;

    };

    /**
     * Adds an edge to the graph object from a given vertex.
     *
     * @param u a vertex where the edge is originating from.
     * @param v the end location of the edge, the vertex being attached to.
     */
    public void addEdge(final int u, final int v){

        if (!directed) {
            adjacencyMatrix[u][v] = 1;
        }

        adjacencyMatrix[v][u] = 1;

    }

    public int degree(final int v){
        int degree = 0;


       for(int eachEdge: adjacencyMatrix[v]){
           if(eachEdge == 1){
               degree += 1;
           }
       }


        return degree;
    }

    public void setDirection(boolean directed){
        this.directed = directed;
    }

    public boolean getDirection(){
        return this.directed;
    }


    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append("  ");

        for(int i = 0; i < adjacencyMatrix.length; i++){
            data.append(i).append(" ");
        }
        data.append("\n");

        int count = 0;
        for (int[] matrix : adjacencyMatrix) {
            data.append(count).append(" ");
            for (int i : matrix) {
                data.append(i).append(" ");
            }
            data.append("\n");
            count++;
        }
        return data.toString();
    }

    int inDegree(int v){
        // anything coming into v
        int degree = 0;
        for(int[] eachRow: adjacencyMatrix){
            if(eachRow[v] == 1){
                degree++;
            }
        }
        return degree;
    }

    int outDegree(int v){
        int outDegree = 0;

        for(int each: adjacencyMatrix[v]){
            if(each == 1){
                outDegree++;
            }
        }
        return outDegree;
    }

    public int recurseMatrix(int vertex,  ArrayList<Integer> visitedNodes){

        int tempVertex;

        if(this.adjacencyMatrix.length == 1 ){
            visitedNodes.add(vertex);
            System.out.println("Vertex: "+ vertex);
            return vertex;
        }

        if(visitedNodes.size() == this.adjacencyMatrix.length){
            return vertex;
        }

        for(int i = 0; i < adjacencyMatrix[vertex].length; i++){
            if(adjacencyMatrix[vertex][i] == 1){
                 tempVertex = i;
                if(!visitedNodes.contains(tempVertex)){
                    vertex = i;
                    System.out.println("Vertex here: "+ vertex);
                    visitedNodes.add(vertex);
                    break;
                }
            }
        }

        return recurseMatrix(vertex,visitedNodes);
    }

    public void DFS(){
        int startingVertexIndex = 0;
        int visited = -1;

        ArrayList<Integer> visitedNodes = new ArrayList<>();

        while(visited != startingVertexIndex){
            visited = recurseMatrix(startingVertexIndex,visitedNodes);

        }
    }

    public static void main(final String[] args){

        Graph graph = new Graph(5);
        graph.setDirection(true);
        graph.addEdge(0,0);
        graph.addEdge(4,0);
        graph.addEdge(2,1);
        graph.addEdge(4,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(2,3);
        graph.addEdge(3,2);
        graph.addEdge(3,4);
        System.out.println(graph);
//      //  System.out.println("\n");
//        System.out.println(graph.degree(2));
//
//        System.out.println(graph.inDegree(2));
//        System.out.println(graph.outDegree(4));
          graph.DFS();
    }
}
