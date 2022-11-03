import java.lang.reflect.Array;
import java.util.ArrayList;

public class Graph {

    int[][] adjacencyMatrix;
    boolean directed;

    public Graph(final int v) {
        adjacencyMatrix = new int[v][v];
        directed = false;

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

    public int degree(final int v) {
        int degree = 0;


        for (int eachEdge : adjacencyMatrix[v]) {
            if (eachEdge == 1) {
                degree += 1;
            }
        }


        return degree;
    }

    public void setDirection(boolean directed) {
        this.directed = directed;
    }

    public boolean getDirection() {
        return this.directed;
    }


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

    int inDegree(int v) {
        // anything coming into v
        int degree = 0;
        for (int[] eachRow : adjacencyMatrix) {
            if (eachRow[v] == 1) {
                degree++;
            }
        }
        return degree;
    }

    int outDegree(int v) {
        int outDegree = 0;

        for (int each : adjacencyMatrix[v]) {
            if (each == 1) {
                outDegree++;
            }
        }
        return outDegree;
    }

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

    public void DFS() {
        int startingVertexIndex = 0;
        int visited = -1;

        ArrayList<Integer> visitedNodes = new ArrayList<>();

        while (visited != startingVertexIndex) {
            visited = traverseNodes(startingVertexIndex, visitedNodes);
        }
        System.out.println(visitedNodes);

    }


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

        Graph gr = new Graph(5);
        gr.setDirection(true);
        gr.addEdge(0, 0);
        gr.addEdge(4, 0);
        gr.addEdge(2, 1);
        gr.addEdge(4, 1);
        gr.addEdge(0, 2);
        gr.addEdge(1, 3);
        gr.addEdge(2, 3);
        gr.addEdge(3, 2);
        gr.addEdge(3, 4);
        System.out.println(gr);

        gr.BFS();
        System.out.println("\n");


        Graph G = new Graph(8);
        G.addEdge(0, 1); //ab
        G.addEdge(0, 4); //ae
        G.addEdge(0, 5); //af
        G.addEdge(1, 5); //bf
        G.addEdge(1, 6); //bg
        G.addEdge(2, 3); //cd
        G.addEdge(2, 6); //cg
        G.addEdge(3, 7); //dh
        G.addEdge(4, 5); //ef
        G.addEdge(6, 7); //gh

        System.out.println(G);
        G.DFS();
        G.BFS();


        Graph graph = new Graph(8);
        graph.setDirection(true);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(1, 0);
        graph.addEdge(1, 3);
        graph.addEdge(1, 5);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(2, 6);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);
        graph.addEdge(3, 7);
        graph.addEdge(4, 0);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 1);
        graph.addEdge(5, 4);
        graph.addEdge(5, 7);
        graph.addEdge(6, 2);
        graph.addEdge(6, 4);
        graph.addEdge(6, 7);
        graph.addEdge(7, 3);
        graph.addEdge(7, 5);
        graph.addEdge(7, 6);

        System.out.println(graph);

        graph.BFS();


    }
}
