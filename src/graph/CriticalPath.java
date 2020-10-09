package graph;

import java.util.LinkedList;
import java.util.List;

// 求一个AOE(Activity On Edge)网络的关键路径
// 这里假定这个AOE图是只有一个源点和一个汇点
public class CriticalPath<T> {
    private final Graph<T> graph;

    public CriticalPath(Graph<T> _graph) {
        graph = _graph;
    }

    /*
    我们把有向边表示活动，
    顶点表示活动的状态： 表示入边的活动已经完成，出边的活动可以开始.
    我们也称顶点为事件
    e[i]: 表示活动i的最早发生时间
    l[i]: 表示活动i的最晚发生时间
    ve[i]: 表示事件v_{i}的最早发生时间
    vl[i]: 表示事件v_{i}的最晚发生时间
     */
    public List<Integer> getCriticalPathIndex() {
        List<Integer> res = new LinkedList<>();

        // 首先先拓扑排序
        // 因为计算ve[i]需要知道它的所有前驱的最早发生时间，计算vl[i]需要知道它的所有后继的最晚发生时间
        TopoSort<T> topoSort = new TopoSort<>(graph);
        List<Integer> topologicalIndex = topoSort.topoSortWithIndex();
        // topological sort test is ok!
        //System.out.println("toposort: "+topoSort.topoSortWithItem());
        int n = graph.getVertexNumber();
        // 计算ve[i]
        int[] ve = new int[n];
        for (int i = 0; i < n; i++) {
            ve[i] = 0;
        }

        // 按照拓扑排序遍历
        for (int i : topologicalIndex) {
            for (int next : graph.getLinkedEdge(i)) {
                if (ve[next] < ve[i] + graph.getWeight(i, next)) {
                    ve[next] = ve[i] + graph.getWeight(i, next);
                }
            }
        }

        // 计算vl
        int[] vl = new int[n];
        for (int i = 0; i < n; i++) {
            vl[i] = Integer.MAX_VALUE;
        }

        // 初始化vl
        int lastVertexIndex = topologicalIndex.get(n - 1);
        vl[lastVertexIndex] = ve[lastVertexIndex];

        // reversed topological order
        for (int j = n - 1; j >= 0; j--) {
            int i = topologicalIndex.get(j);
            for (int next : graph.getLinkedEdge(i)) {
                if (vl[i] > vl[next] - graph.getWeight(i, next)) {
                    vl[i] = vl[next] - graph.getWeight(i, next);
                }
            }
        }

        boolean[] flags = new boolean[n];
        for (int i = 0; i < n; i++) {
            flags[i] = false;
        }

        int e, l;
        // 求活动的最早开始时间和最晚开始时间
        // 遍历每一条边, <i,next>的最早开始时间是ve[i]，最晚开始时间是vl[next] - weight(i,next)
        for (int i = 0; i < n; i++) {
            for (int next : graph.getLinkedEdge(i)) {
                // <i,next>
                e = ve[i];
                l = vl[next] - graph.getWeight(i, next);
                if (e == l) { // 说明这一条边是关键活动
                    flags[i] = true;
                    flags[next] = true;
                }
            }
        }

        // 结果按照拓扑顺序保存
        for (int i : topologicalIndex) {
            if (flags[i]) {
                res.add(i);
            }
        }

        // 验证ve,vl
        /*
        for(int i:topologicalIndex){
            System.out.println(graph.getNode(i)+": ve is "+ve[i]+", vl is "+vl[i]);
        }
        */
        return res;
    }

    public List<T> getCriticalPathItem() {
        List<T> res = new LinkedList<>();
        for (int i : getCriticalPathIndex()) {
            res.add(graph.getNode(i));
        }
        return res;
    }

}
