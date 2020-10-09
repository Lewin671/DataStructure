package graph;

import java.util.*;

// 深度优先遍历算法和广度优先遍历算法
// 这里图的存储结构为邻接表存储
public class Traversal<T> {
    Graph<T> graph;
    boolean[] visited;
    int N; // 结点的最大个数

    public Traversal(Graph<T> _graph) {
        graph = _graph;
        N = graph.getVertexNumber();
        visited = new boolean[N];
    }

    private List<T> depthFirstTraversal(int cur) {
        visited[cur] = true;
        List<T> res = new ArrayList<>();
        res.add(graph.getNode(cur));

        for (int next : graph.getLinkedEdge(cur)) {
            if (!visited[next]) {
                res.addAll(depthFirstTraversal(next));
            }
        }
        return res;
    }


    public List<T> depthFirstTraversal(T startNode) {
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }
        return depthFirstTraversal(graph.getIndex(startNode));
    }

    private List<T> widthFirstTraversal(int startIndex) {
        // 初始化
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startIndex);
        visited[startIndex] = true;

        LinkedList<T> res = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }

        // 迭代
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(graph.getNode(cur));

            // 下一层
            for (int next : graph.getLinkedEdge(cur)) {
                if (!visited[next]) { // 如果下一个结点没有被访问过
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        return res;
    }

    public List<T> widthFirstTraversal(T startNode) {
        return widthFirstTraversal(graph.getIndex(startNode));
    }
}
