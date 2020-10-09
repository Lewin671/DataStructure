package graph;
// 拓扑排序


import java.util.LinkedList;
import java.util.List;

// 求一个AOV(Activity On Vertex)的topological order
public class TopoSort<T> {
    private Graph<T> graph;

    public TopoSort(Graph<T> _graph) {
        this.graph = _graph;
    }

    // 返回一个整数序列的拓扑排序，还需要对照顶点表
    public List<Integer> topoSortWithIndex() {
        List<Integer> res = new LinkedList<>();

        // 初始化，计算每个顶点的入度
        int n = graph.getVertexNumber();
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[i] = 0;
        }
        // 计算初始化的每个结点的入度
        for (int i = 0; i < n; i++) {
            for (int adjVertex : graph.getLinkedEdge(i)) {
                count[adjVertex]++;
            }
        }
        int top = -1; // 栈顶指针

        // 将入度为0的顶点入栈
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                count[i] = top;
                top = i;
            }
        }

        // 拓扑排序
        for (int i = 0; i < n; i++) {
            // 不是AOV图，存在环
            if (top == -1) {
                try {
                    throw new Exception("this is not a AOV graph, because there exists a circle in this network");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // 出栈
                int cur = top;
                top = count[top];

                res.add(cur);

                // 更新该节点的其他邻接结点
                for (int next : graph.getLinkedEdge(cur)) {
                    count[next]--;
                    if (count[next] == 0) { // 如果下一个结点的入度为0，则入栈
                        count[next] = top;
                        top = next;
                    }
                }
            }
        }

        return res;
    }

    public List<T> topoSortWithItem() {
        List<T> res = new LinkedList<>();
        for (int i : topoSortWithIndex()) {
            res.add(graph.getNode(i));
        }
        return res;
    }
}
