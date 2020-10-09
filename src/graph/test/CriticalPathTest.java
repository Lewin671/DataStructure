package graph.test;

import graph.CriticalPath;
import graph.Graph;

import java.util.Scanner;

public class CriticalPathTest {
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

        CriticalPath<String> criticalPath = new CriticalPath<>(graph);
        //System.out.println(criticalPath.getCriticalPathIndex());
        System.out.println(criticalPath.getCriticalPathItem());

        in.close();
    }
}
/*
test data:
12
T1 T2 6
T1 T3 4
T1 T4 5
T2 T5 1
T3 T5 1
T3 T6 1
T4 T6 2
T5 T7 9
T5 T8 8
T6 T8 4
T7 T9 2
T8 T9 4
*/

/*
test2:
12
T2 T5 1
T3 T5 1
T3 T6 1
T4 T6 2
T5 T7 9
T5 T8 8
T6 T8 4
T7 T9 2
T8 T9 4
T1 T2 6
T1 T3 4
T1 T4 5
 */