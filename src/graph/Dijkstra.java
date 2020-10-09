package graph;

import java.util.LinkedList;
import java.util.List;

// 迪杰斯特拉算法
// 适用于: 非负权图的单源点最短路径
public class Dijkstra<T> {
    private final Graph<T> graph;

    public Dijkstra(Graph<T> _graph) {
        graph = _graph;
    }

    // 记录从源点v0到其他点的最短路径
    public int[] dijkstraTree(int v0) {
        int n = graph.getVertexNumber();
        int[] path = new int[n];

        // 初始化
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }

        visited[v0] = true;
        dist[v0] = 0;

        // 迭代
        // S: 已经求得是最短路径的顶点
        // 迭代过程就是不断地从V-S中找到最小的，然后加入S
        int cur = v0;
        while (true) {
            // 更新cur结点的邻接顶点的最短距离
            for (int adj : graph.getLinkedEdge(cur)) {
                if (!visited[adj] && dist[adj] > dist[cur] + graph.getWeight(cur, adj)) {
                    dist[adj] = dist[cur] + graph.getWeight(cur, adj);
                    path[adj] = cur;
                }
            }

            // 找出下一个最短距离的结点
            boolean flag = false;
            int dmax = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[i] < dmax) {
                    flag = true;
                    dmax = dist[i];
                    cur = i;
                }
            }

            // 如果最短无法更新，则退出
            if (!flag) {
                break;
            }
            visited[cur] = true;
        }

        return path;
    }

    // 计算从源点from到目的结点to的最短路径
    public List<Integer> dijkstraPath(int from, int to) {
        LinkedList<Integer> res = new LinkedList<>();
        int[] path = dijkstraTree(from);

        if (path[to] == -1) return res;

        int cur = to;
        while (cur != -1) {
            res.addFirst(cur);
            cur = path[cur];
        }

        return res;
    }

    // 计算从源点from到目的结点to的最短路径
    public List<T> dijkstraPath(T from, T to) {
        List<Integer> path = dijkstraPath(graph.getIndex(from), graph.getIndex(to));
        List<T> res = new LinkedList<>();
        for (int i : path) {
            res.add(graph.getNode(i));
        }
        return res;
    }

    // 计算从源点到目的结点的最短距离
    public int dijkstraDistance(int from, int to) {
        List<Integer> path = dijkstraPath(from, to);
        if (path.size() < 2) {
            return Integer.MAX_VALUE;
        }

        int res = 0;
        for (int i = 1; i < path.size(); i++) {
            res += graph.getWeight(path.get(i - 1), path.get(i));
        }
        return res;
    }

    public int dijkstraDistance(T from, T to) {
        return dijkstraDistance(graph.getIndex(from), graph.getIndex(to));
    }
}
