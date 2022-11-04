/**
 * Lab 7: Data Structures and Graphs.
 *
 * @name: Bradley Masciotra.
 * @student_number: A01247718
 * @date: November 4th 2022
 * @version: 1.0
 *
 * @description:
 *
 * Lab 7 involves creating an adjacency matrix based on a corresponding graph where the numerical value of 1
 * represents an edge in the graph. The file contains methods for a Depth first search(DFS) and Breadth first search(BFS)
 * algorithms to find the correct corresponding path. Graphs have the option to be direction or not which will
 * impact the given functions for inDegree and outDegree which measure the amount of incoming edges and outgoing edges.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    int[][] adjacencyMatrix;
    boolean directed;

    /**
     * Graph constructor.
     *
     * Creates an adjacency matrix based on the value v passed.
     *
     * @param v an integer representing the dimensions of the adjacency matrix.
     */
    public Graph(final int v) {
        adjacencyMatrix = new int[v][v];
        directed = false;
    }

    /**
     * Alternative graph constructor.
     *
     * Creates an adjacency matrix based on the value v passed and whether the graph is directed or not.
     *
     * @param v an integer representing the dimensions of the adjacency matrix.
     * @param directed boolean representing whether the graph is directed or not.
     */
    public Graph(final int v, final boolean directed) {
        adjacencyMatrix = new int[v][v];
        this.directed = directed;
    }

    ;

    /**
     * Adds an edge to the graph object from a given vertex.
     *
     * @param u a vertex where the edge is originating from.
     * @param v the end location of the edge, the vertex being attached to.
     */
    public void addEdge(final int u, final int v) {

        if (!directed) {
            adjacencyMatrix[u][v] = 1;
        }

        adjacencyMatrix[v][u] = 1;
    }

    /**
     * Gets the degrees to the given vertex provided.
     *
     * @param v the vertex we are inquiring about.
     * @return the number of degrees/edges pertaining to the vertex.
     */
    public int degree(final int v) {
        int degree = 0;

        for (int eachEdge : adjacencyMatrix[v]) {
            if (eachEdge == 1) {
                degree += 1;
            }
        }

        return degree;
    }

    /**
     * Sets the direction of the graph.
     *
     * @param directed a boolean representing whether the graph is directed or not.
     */
    public void setDirection(boolean directed) {
        this.directed = directed;
    }

    /**
     * Gets the direction of the graph, whether it is directed or not.
     *
     * @return the graph's direction.
     */
    public boolean getDirection() {
        return this.directed;
    }

    /**
     * Returns the adjacency matrix transposed as a string of the graph.
     *
     * @return the adjacency matrix transposed as a string of the graph.
     */
    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        data.append("  ");

        for (int i = 0; i < adjacencyMatrix.length; i++) {
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

    /**
     * gets the amount of incoming degrees into the given vertex.
     *
     * @param v a given vertex.
     * @return the amount of incoming degrees on a given vertex.
     */
    int inDegree(int v) {
        int degree = 0;

        if(directed){
            int[] vertexRow = adjacencyMatrix[v];

            for(int eachCell: vertexRow){
                if(eachCell == 1){
                    degree++;
                }
            }
        } else {
            for (int[] eachRow : adjacencyMatrix) {
                if (eachRow[v] == 1) {
                    degree++;
                }
            }

        }
        return degree;
    }

    /**
     * gets the amount of outgoing degrees into the given vertex.
     *
     * @param v a given vertex.
     * @return the amount of outgoing degrees on a given vertex.
     */
    int outDegree(int v) {
        int outDegree = 0;

        for (int each : adjacencyMatrix[v]) {
            if (each == 1) {
                outDegree++;
            }
        }
        return outDegree;
    }

    /**
     * Traverses the vertexes given neighbours and returns the next vertex to advance to.
     *
     * @param vertex the vertex we are analyzing.
     * @param visitedNodes the list of visited nodes.
     * @return the next vertex to traverse to.
     */
    public int traverseNodes(int vertex, ArrayList<Integer> visitedNodes) {
        int tempVertex;

        if (this.adjacencyMatrix.length == 1) {
            visitedNodes.add(vertex);
            return vertex;
        }

        // this condition is met because all nodes have been visited therefore ending the DFS.
        if (visitedNodes.size() == this.adjacencyMatrix.length) {
            return vertex;
        }

        for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                tempVertex = i;
                if (!visitedNodes.contains(tempVertex)) {

                    if (!visitedNodes.contains(vertex)) {
                        System.out.println("Visiting Vertex: " + vertex);

                        visitedNodes.add(vertex);
                    }
                    vertex = tempVertex;
                    break;
                }
            } else {
                // this constitutes there are no more options.
                if (i == adjacencyMatrix[vertex].length - 1) {
                    if (!visitedNodes.contains(vertex)) {
                        System.out.println("Visiting Vertex: " + vertex);
                        visitedNodes.add(vertex);
                    }

                    vertex = visitedNodes.get(visitedNodes.indexOf(vertex) - 1);
                }
            }
        }
        return traverseNodes(vertex, visitedNodes);
    }

    /**
     * Traverses the nodes in the DFS algorithm recursively building the given visited list based on visiting
     * the next available neighbouring vertex with an edge, if none is available the algorithm backtracks to a
     * neighbour with an available neighbour if none is available the algorithm is complete.
     */
    public void DFS() {
        int startingVertexIndex = 0;
        int visited = -1;

        ArrayList<Integer> visitedNodes = new ArrayList<>();

        while (visited != startingVertexIndex) {
            visited = traverseNodes(startingVertexIndex, visitedNodes);
        }
        System.out.println(visitedNodes);

    }


    /**
     * Traverses the nodes based on a BFS algorithm. Starting at a given vertex we traverse the adjacency matrix
     * based on the vertexes available neighbours. The algorithm travels to a given vertex, records all its neighbours,
     * and when all the neighbours have been visited moves to the first neighbour from the stack until the traversal
     * is complete.
     */
    public void BFS(){
        int startingVertexIndex = 0;
        int visited = -1;

        ArrayList<Integer> visitedNodes = new ArrayList<>();
        ArrayList<Integer> stack        = new ArrayList<>();
        visitedNodes.add(startingVertexIndex);

        while (visited != startingVertexIndex) {
            visited = traverseNodesBFS(startingVertexIndex, visitedNodes, stack);
        }
    }

    /**
     * Records the given vertexes neighbours and if they have not been visited, adds them to the stack and then
     * visits the first neighbour popped from the stack recursively until all neighbours have been visited.
     *
     * @param vertex a given vertex.
     * @param visitedNodes a list of the visited nodes.
     * @param stack a list of all the vertexes previously visited.
     * @return the next vertex to traverse to.
     */
    private int traverseNodesBFS(int vertex, ArrayList<Integer> visitedNodes, ArrayList<Integer> stack) {

        if (this.adjacencyMatrix.length == 1) {
            visitedNodes.add(vertex);
            return vertex;
        }

        if (stack.size() == this.adjacencyMatrix.length) {
            return vertex;
        }

        for(int i = 0; i < adjacencyMatrix.length; i++){
            if(adjacencyMatrix[vertex][i] == 1){
                visitedNodes.add(i);
            }
        }

        if(!stack.contains(vertex)) {
            stack.add(vertex);
            System.out.println("Visiting: " + stack.get(stack.size()-1));

        }
        visitedNodes.remove(0);
        vertex = visitedNodes.get(0);

        return traverseNodesBFS(vertex,visitedNodes,stack);
    }

    public static void main(final String[] args) {

        Graph graph1 = new Graph(5);
        graph1.setDirection(true);
        graph1.addEdge(0,1);
        graph1.addEdge(0,3);
        graph1.addEdge(0,4);
        graph1.addEdge(1,0);
        graph1.addEdge(1,2);
        graph1.addEdge(1,4);
        graph1.addEdge(2,1);
        graph1.addEdge(2,3);
        graph1.addEdge(3,0);
        graph1.addEdge(3,2);
        graph1.addEdge(3,4);
        graph1.addEdge(4,0);
        graph1.addEdge(4,1);
        graph1.addEdge(4,3);

        System.out.println(graph1);
        System.out.println(graph1.inDegree(1));


        Graph graph2 = new Graph(4);
        graph2.addEdge(0,1);
        graph2.addEdge(1,0);
        graph2.addEdge(1,2);
        graph2.addEdge(2,1);
        graph2.addEdge(2,3);
        graph2.addEdge(3,2);

        System.out.println(graph2);

        Graph graph3 = new Graph(6);
        graph3.addEdge(0,2);
        graph3.addEdge(0,4);
        graph3.addEdge(1,3);
        graph3.addEdge(1,5);
        graph3.addEdge(2,0);
        graph3.addEdge(2,4);
        graph3.addEdge(3,1);
        graph3.addEdge(3,5);
        graph3.addEdge(4,0);
        graph3.addEdge(4,2);
        graph3.addEdge(5,1);
        graph3.addEdge(5,3);

        System.out.println(graph3);
    }
}
