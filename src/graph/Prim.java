package graph;

import java.util.LinkedList;
import java.util.List;

// prim算法求解最小生成树（最小支撑树）
public class Prim<T> {
    private Graph<T> graph;
    private int n;

    public Prim(Graph<T> _graph) {
        graph = _graph;
        n = graph.getVertexNumber();
    }

    // 通过prim算法，求解一个连通图无向图的最小支撑树的边
    // 注意： 最小支撑树不一定唯一
    // 算法思想: 把构造最小生成树的过程分为两个集合U和V-U
    // 其中U是已构造的最小生成树子集的集合，prim算法就是从V-U中挑选一个顶点v
    // 使得for all u in U和 for all v in V-U，选择出min{cost(u,v)}的边(u,v)
    // 这条边必定是某最小生成树的一条边
    public List<Edge> primEdges() {
        List<Edge> res = new LinkedList<>();

        // vertex表示V-U集合中，对于vertex[v]，表示的是v与U中最近的点u
        // 而(u,v)的代价就是lowestCost[v]
        // 如果v已经加入集合U中，则vertex[v] = -1,lowestCost[v] = 0
        int[] vertex = new int[n];
        int[] lowestCost = new int[n];

        // 初始化，默认初始化顶点为0
        for (int i = 0; i < n; i++) {
            vertex[i] = 0;
            lowestCost[i] = graph.getWeight(0, i);
        }

        // 将顶点0加入集合U
        vertex[0] = -1;
        lowestCost[0] = 0;

        // 迭代，不断地从V-U中选择与U最近的点v
        for (int i = 0; i < n - 1; i++) {
            // 选择与U中最近的点v
            int v = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (vertex[j] != -1 && lowestCost[j] < min) {
                    v = j;
                    min = lowestCost[j];
                }
            }

            // 说明原图不是一个连通图
            if (v == -1) {
                try {
                    throw new Exception("the origin graph isn't a connected network");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 将v加入U中
            res.add(new Edge(vertex[v], v));
            vertex[v] = -1;
            lowestCost[v] = 0;

            // 更新vertex和lowestCost
            for (int j = 0; j < n; j++) {
                if (vertex[j] != -1 && lowestCost[j] > graph.getWeight(v, j)) {
                    vertex[j] = v;
                    lowestCost[j] = graph.getWeight(v, j);
                }
            }
        }
        return res;
    }


    public int primCost() {
        List<Edge> edges = primEdges();
        int res = 0;
        for (Edge e : edges) {
            res += graph.getWeight(e.from, e.to);
        }
        return res;
    }

    public void printPrimEdges() {
        List<Edge> edges = primEdges();
        for (Edge e : edges) {
            System.out.println("<" + graph.getNode(e.from) + "," + graph.getNode(e.to) + ">");
        }
    }
}
