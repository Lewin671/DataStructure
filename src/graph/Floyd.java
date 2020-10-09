package graph;

import java.util.LinkedList;
import java.util.List;

public class Floyd<T> {
    private Graph<T> graph;
    private int n; // 顶点个数
    private int[][] edge; // 邻接矩阵
    private int[][] path; // 记录从i -> j的最短路径上，j的前一个序号
    private int[][] dis; // 记录 i->j的最短路径长度

    public Floyd(Graph<T> _graph) {
        graph = _graph;
        n = graph.getVertexNumber();
        edge = new int[n][n];
        path = new int[n][n];
        dis = new int[n][n];

        // 构造邻接矩阵
        // 注意: edge[i][i] = infinity
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edge[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j : graph.getLinkedEdge(i)) {
                edge[i][j] = graph.getWeight(i, j);
            }
        }
    }

    public void floyd() {
        // 初始化path和dis
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = edge[i][j];

                if (edge[i][j] < Integer.MAX_VALUE) {
                    path[i][j] = i;
                } else {
                    path[i][j] = -1;
                }
            }
        }

        // 开始迭代dis[i][j]
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (k != i) {
                    for (int j = 0; j < n; j++) {
                        if (j != i && j != k
                                && dis[i][k] != Integer.MAX_VALUE
                                && dis[k][j] != Integer.MAX_VALUE
                                && dis[i][j] > dis[i][k] + dis[k][j]) {
                            dis[i][j] = dis[i][k] + dis[k][j];
                            path[i][j] = path[k][j];
                            // System.out.println("更新 "+i+","+j);
                        }
                    }
                }
            }
        }
    }

    public List<Integer> floydPath(int from, int to) {
        LinkedList<Integer> res = new LinkedList<>();
        if (dis[from][to] == Integer.MAX_VALUE) {
            return res;
        }
        int cur = to;
        while (cur != -1) {
            res.addFirst(cur);
            cur = path[from][cur];
        }
        return res;
    }

    public List<T> floydPath(T from, T to) {
        LinkedList<T> res = new LinkedList<>();
        for (int i : floydPath(graph.getIndex(from), graph.getIndex(to))) {
            res.add(graph.getNode(i));
        }
        return res;
    }

    public int floydDistance(int from, int to) {
        return dis[from][to];
    }

    public int floydDistance(T from, T to) {
        return floydDistance(graph.getIndex(from), graph.getIndex(to));
    }
}
