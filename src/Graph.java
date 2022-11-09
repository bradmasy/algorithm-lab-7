/**
 * Lab 7: Data Structures and Graphs.
 *
 * @name: Bradley Masciotra.
 * @student_number: A01247718
 * @date: November 4th 2022
 * @version: 1.0
 * @description: Lab 7 involves creating an adjacency matrix based on a corresponding graph where the numerical value of 1
 * represents an edge in the graph. The file contains methods for a Depth first search(DFS) and Breadth first search(BFS)
 * algorithms to find the correct corresponding path. Graphs have the option to be direction or not which will
 * impact the given functions for inDegree and outDegree which measure the amount of incoming edges and outgoing edges.
 */

import java.util.ArrayList;

public class Graph {

    int[][] adjacencyMatrix;
    boolean directed;
    ArrayList<String> verticesLabels;

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

    public Graph(final ArrayList<String> vertices){
        verticesLabels = vertices;
        // create a new matrix based on the size of vertices passed;
        adjacencyMatrix = new int[verticesLabels.size()][verticesLabels.size()];
        directed = false;
    }

//    /**
//     * Adds an edge to the graph object from a given vertex.
//     *
//     * @param u a vertex where the edge is originating from.
//     * @param v the end location of the edge, the vertex being attached to.
//     */
//    public void addEdge(final int u, final int v) {
//
//        if (!directed) {
//            adjacencyMatrix[u][v] = 1;
//            adjacencyMatrix[v][u] = 1;
//        } else {
//            adjacencyMatrix[u][v] = 1;
//        }
//    }

    public void addEdge(final String a, final String b){

        // get the location in the labels
        int aIndex = verticesLabels.indexOf(a);
        int bIndex = verticesLabels.indexOf(b);

        if(!directed){
            adjacencyMatrix[aIndex][bIndex] = 1;
            adjacencyMatrix[bIndex][aIndex] = 1;
        } else {
            adjacencyMatrix[aIndex][bIndex] = 1;
        }
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
        data.append("    ");

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            data.append(verticesLabels.get(i)).append(" ");
        }
        data.append("\n");
        for(int y = 0; y < adjacencyMatrix.length;y++){
            data.append("---");
        }
        data.append("\n");

        int count = 0;
        for (int[] matrix : adjacencyMatrix) {
            data.append(verticesLabels.get(count)).append(" |").append(" ");
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

        if (directed) {
            for (int[] vertexRow : adjacencyMatrix) {
                if (vertexRow[v] == 1) {
                    degree++;
                }
            }
        } else {
            degree = -1; // must be directed for in degree to work.
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

        if(directed){
            for (int each : adjacencyMatrix[v]) {
                if (each == 1) {
                    outDegree++;
                }
            }
        } else {
            outDegree = -1; // must be directed for out degree to work.
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
    public int traverseNodes(int vertex, ArrayList<String> visitedNodes) {
        int tempVertex;

        if (this.adjacencyMatrix.length == 1) {
            visitedNodes.add(verticesLabels.get(vertex));
            return vertex;
        }

        // this condition is met because all nodes have been visited therefore ending the DFS.
        if (visitedNodes.size() == this.adjacencyMatrix.length) {
            return vertex;
        }

        for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                tempVertex = i;
                if (!visitedNodes.contains(verticesLabels.get(tempVertex))) {

                    if (!visitedNodes.contains(verticesLabels.get(vertex))) {
                        System.out.println("Visiting Vertex: " + verticesLabels.get(vertex));

                        visitedNodes.add(verticesLabels.get(vertex));
                    }
                    vertex = tempVertex;
                    break;
                }
            } else {
                // this constitutes there are no more options.
                if (i == adjacencyMatrix[vertex].length - 1) {
                    if (!visitedNodes.contains(verticesLabels.get(vertex))){
                        visitedNodes.add(verticesLabels.get(vertex));
                    }
                    vertex = visitedNodes.indexOf((visitedNodes.get(vertex - 1))) ;
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

        ArrayList<String> visitedVertices = new ArrayList<>();

        while (visited != startingVertexIndex) {
            visited = traverseNodes(startingVertexIndex, visitedVertices);
        }
        System.out.println(visitedVertices);
    }


    /**
     * Traverses the nodes based on a BFS algorithm. Starting at a given vertex we traverse the adjacency matrix
     * based on the vertexes available neighbours. The algorithm travels to a given vertex, records all its neighbours,
     * and when all the neighbours have been visited moves to the first neighbour from the stack until the traversal
     * is complete.
     */
    public void BFS() {
        int startingVertexIndex = 0;
        int visited = -1;

       // ArrayList<Integer> visitedNodes = new ArrayList<>();
        ArrayList<String> visitedVertices = new ArrayList<>();


        ArrayList<String> stack = new ArrayList<>();
        visitedVertices.add(verticesLabels.get(startingVertexIndex));

        while (visited != startingVertexIndex) {
            visited = traverseNodesBFS(startingVertexIndex, visitedVertices, stack);
        }
        System.out.println(stack);
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
    private int traverseNodesBFS(int vertex, ArrayList<String> visitedNodes, ArrayList<String> stack) {

        if (this.adjacencyMatrix.length == 1) {
            visitedNodes.add(verticesLabels.get(vertex));
            return vertex;
        }

        if (stack.size() == this.adjacencyMatrix.length) {
            return vertex;
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                visitedNodes.add(verticesLabels.get(i));
            }
        }

        if (!stack.contains(verticesLabels.get(vertex))) {
            stack.add(verticesLabels.get(vertex));
           // System.out.println("BFS Visiting: " + stack.get(stack.size() - 1));

        }
        visitedNodes.remove(0);
        vertex = verticesLabels.indexOf(visitedNodes.get(0));//visitedNodes.get(0);

        return traverseNodesBFS(vertex, visitedNodes, stack);
    }
}
