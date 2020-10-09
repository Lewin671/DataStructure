package graph;

import tree.application.UnionFindSet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Kruskal<T> {
    private final Graph<T> graph;
    private final int n;

    public Kruskal(Graph<T> _graph) {
        graph = _graph;
        n = graph.getVertexNumber();
    }

    public List<Edge> kruskalEdges() {
        List<Edge> res = new LinkedList<>();
        UnionFindSet unionFindSet = new UnionFindSet(n);
        int cnt = 0;

        // 构建边集合
        List<WeightEdge> edges = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j : graph.getLinkedEdge(i)) {
                WeightEdge edge = new WeightEdge(i, j, graph.getWeight(i, j));
                edges.add(edge);
            }
        }

        // 排序
        Comparator<WeightEdge> comparator = new Comparator<WeightEdge>() {
            @Override
            public int compare(WeightEdge o1, WeightEdge o2) {
                return o1.weight - o2.weight;
            }
        };
        edges.sort(comparator);

        // 遍历每一条边
        for (WeightEdge e : edges) {
            if (unionFindSet.find(e.from) != unionFindSet.find(e.to)) {
                res.add(new Edge(e.from, e.to));
                unionFindSet.union(e.from, e.to);
                cnt++;
                if (cnt == n - 1) {
                    break;
                }
            }
        }
        return res;
    }

    public void printKruskalEdges() {
        for (Edge e : kruskalEdges()) {
            System.out.println("<" + graph.getNode(e.from) + "," + graph.getNode(e.to) + ">");
        }
    }

    public int kruskalCost() {
        int res = 0;
        for (Edge e : kruskalEdges()) {
            res += graph.getWeight(e.from, e.to);
        }
        return res;
    }
}
