package graph.test;

import graph.Graph;
import graph.Traversal;

import java.util.Scanner;

public class TraversalTest {
    /*
    4
    beijing shanghai
    beijing shenzhen
    shenzhen shantou
    shantou shenzhen
    beijing

    4
    A B
    B C
    B D
    A E
    A
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph<String> graph = new Graph<>();
        int n = in.nextInt();
        String from;
        String to;
        for (int i = 0; i < n; i++) {
            from = in.next();
            to = in.next();
            graph.addEdge(from, to);
        }
        String startNode = in.next();

        Traversal<String> traversal = new Traversal<>(graph);
        System.out.println(traversal.depthFirstTraversal(startNode));
        System.out.println(traversal.widthFirstTraversal(startNode));

        in.close();
    }
}
