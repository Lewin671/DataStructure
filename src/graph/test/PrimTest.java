package graph.test;

import graph.Graph;
import graph.Prim;

import java.util.Scanner;

public class PrimTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph<String> graph = new Graph<>();
        int n = in.nextInt(); //n条边
        String from, to;
        int w;
        for (int i = 0; i < n; i++) {
            from = in.next();
            to = in.next();
            w = in.nextInt();
            graph.addUndirectedEdge(from, to, w);
        }

        Prim<String> prim = new Prim<>(graph);
        System.out.println(prim.primCost());
        prim.printPrimEdges();
        in.close();
    }
}
/*
10
1 2 1
1 3 4
1 4 7
2 3 4
3 4 2
2 5 2
5 3 5
3 6 3
5 6 3
6 4 3

 */