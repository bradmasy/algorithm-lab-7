import java.util.ArrayList;

/**
 * Main Method for Graph
 */
public class Main {

    /**
     * Drives the program.
     *
     * @param args program arguments.
     */
    public static void main(final String[] args) {

        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("a");
        vertices.add("b");
        vertices.add("c");
        vertices.add("d");
        vertices.add("e");
        vertices.add("f");

        Graph Graph1 = new Graph(vertices);
        Graph1.addEdge("a", "b");
        Graph1.addEdge("a", "e");
        Graph1.addEdge("a", "f");
        Graph1.addEdge("b", "c");
        Graph1.addEdge("d", "b");
        Graph1.addEdge("d", "c");
        Graph1.addEdge("e", "d");
        Graph1.addEdge("f", "c");
        Graph1.addEdge("f", "e");


        System.out.println(Graph1);
        Graph1.DFS();
        Graph1.BFS();

        ArrayList<String> vertices2 = new ArrayList<>();
        vertices2.add("socks");
        vertices2.add("shoes");
        vertices2.add("shirt");
        vertices2.add("pants");
        vertices2.add("suspenders");
        vertices2.add("belt");
        vertices2.add("tie");
        vertices2.add("jacket");
        vertices2.add("underwear");

        Graph Graph3 = new Graph(vertices2);
        System.out.println(Graph3);

        Graph3.addEdge("socks", "shoes");
        Graph3.addEdge("shirt", "suspenders");
        Graph3.addEdge("pants", "suspenders");
        Graph3.addEdge("pants", "shoes");
        Graph3.addEdge("pants", "belt");
        Graph3.addEdge("shirt", "tie");
        Graph3.addEdge("shirt", "jacket");
        Graph3.addEdge("suspenders", "jacket");
        Graph3.addEdge("belt", "jacket");
        Graph3.addEdge("tie", "jacket");
        Graph3.addEdge("underwear", "pants");
        System.out.println(Graph3);
        Graph3.DFS();
        Graph3.BFS();
//        Graph graph1 = new Graph(5);
//        System.out.println("----------Graph 1----------");
//        graph1.setDirection(true);
//        graph1.addEdge(0, 1);
//        graph1.addEdge(0, 3);
//        graph1.addEdge(0, 4);
//        graph1.addEdge(1, 0);
//        graph1.addEdge(1, 2);
//        graph1.addEdge(1, 4);
//        graph1.addEdge(2, 1);
//        graph1.addEdge(2, 3);
//        graph1.addEdge(3, 0);
//        graph1.addEdge(3, 2);
//        graph1.addEdge(3, 4);
//        graph1.addEdge(4, 0);
//        graph1.addEdge(4, 1);
//        graph1.addEdge(4, 3);
//
//        System.out.println(graph1);
//        System.out.println("Degree for vertex 1: " + graph1.degree(1));
//        System.out.println("Degree for vertex 3: " + graph1.degree(3));
//
//        System.out.println("----------Graph 2----------");
//
//        Graph graph2 = new Graph(4);
//        graph2.addEdge(0, 1);
//        graph2.addEdge(1, 0);
//        graph2.addEdge(1, 2);
//        graph2.addEdge(2, 1);
//        graph2.addEdge(2, 3);
//        graph2.addEdge(3, 2);
//
//        System.out.println(graph2);
//
//        System.out.println("Degree for vertex 1: " + graph2.degree(1));
//        System.out.println("Degree for vertex 2: " + graph2.degree(2));
//        System.out.println("In degree on bi-directional graph: " + graph2.inDegree(1));
//        System.out.println("Out degree on bi-directional graph: " + graph2.outDegree(2));
//
//        System.out.println("----------Graph 3----------");
//
//        Graph graph3 = new Graph(6);
//        graph3.addEdge(0, 2);
//        graph3.addEdge(0, 4);
//        graph3.addEdge(1, 3);
//        graph3.addEdge(1, 5);
//        graph3.addEdge(2, 0);
//        graph3.addEdge(2, 4);
//        graph3.addEdge(3, 1);
//        graph3.addEdge(3, 5);
//        graph3.addEdge(4, 0);
//        graph3.addEdge(4, 2);
//        graph3.addEdge(5, 1);
//        graph3.addEdge(5, 3);
//
//        System.out.println(graph3);
//
//        System.out.println("Degree for vertex 4: " + graph3.degree(4));
//        System.out.println("Degree for vertex 5: " + graph3.degree(5));
//
//        System.out.println("----------Part 4: Directed Graph----------");
//
//        Graph graph4 = new Graph(5);
//        graph4.setDirection(true);
//
//        graph4.addEdge(0, 0);
//        graph4.addEdge(0, 4);
//        graph4.addEdge(1, 2);
//        graph4.addEdge(1, 4);
//        graph4.addEdge(2, 0);
//        graph4.addEdge(2, 3);
//        graph4.addEdge(3, 1);
//        graph4.addEdge(3, 2);
//        graph4.addEdge(4, 3);
//
//        System.out.println(graph4);
//        System.out.println("In Degree V:0 = " + graph4.inDegree(0));
//        System.out.println("In Degree V:3 = " + graph4.inDegree(3));
//        System.out.println("In Degree V:1 = " + graph4.inDegree(1));
//        System.out.println("Out Degree V:2 = " + graph4.outDegree(2));
//        System.out.println("Out Degree V:3 = " + graph4.outDegree(3));
//        System.out.println("Out Degree V:4 = " + graph4.outDegree(4)+"\n");
//
//
//        System.out.println("----------Part 5: Another Sample----------");
//
//        Graph G = new Graph(8);
//
//        G.addEdge(0, 1); //ab
//        G.addEdge(0, 4); //ae
//        G.addEdge(0, 5); //af
//        G.addEdge(1, 5); //bf
//        G.addEdge(1, 6); //bg
//        G.addEdge(2, 3); //cd
//        G.addEdge(2, 6); //cg
//        G.addEdge(3, 7); //dh
//        G.addEdge(4, 5); //ef
//        G.addEdge(6, 7); //gh
//
//        System.out.println(G);
//        System.out.println("In degree on bi-directional graph: " + G.inDegree(1));
//        System.out.println("Out degree on bi-directional graph: " + G.outDegree(1));
//
//        System.out.println("DFS Traversal of Graph in Part 5.\n");
//        G.DFS();
//        System.out.println("----------Part 6: BFS Traversal of Graph-----------\n");
//        G.BFS();
    }
}
