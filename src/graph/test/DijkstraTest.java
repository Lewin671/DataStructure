package graph.test;

import graph.Dijkstra;
import graph.Graph;

import java.util.Scanner;

public class DijkstraTest {
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
            graph.addEdge(from, to, w);
        }
        Dijkstra<String> dijkstra = new Dijkstra<>(graph);

        System.out.println("请输入起点和终点:");
        String start = in.next();
        String destination = in.next();
        System.out.println(dijkstra.dijkstraDistance(start, destination));
        System.out.println(dijkstra.dijkstraPath(start, destination));

        in.close();
    }
}
