package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


// 可以是有向图和无向图，也可以是有权图和无权图
// 取决于调用的addEdge方法
public class Graph<T> {
    private HashMap<T, Integer> vertexTable;
    private HashMap<Integer, T> inverseVertexTable;
    private HashMap<Edge, Integer> weightTable;
    private ArrayList<LinkedList<Integer>> data;

    public Graph() {
        data = new ArrayList<>();
        vertexTable = new HashMap<>();
        inverseVertexTable = new HashMap<>();
        weightTable = new HashMap<>();
    }

    // 添加一条边
    public void addEdge(T from, T to) {
        if (!vertexTable.containsKey(from)) {
            vertexTable.put(from, vertexTable.size());
            inverseVertexTable.put(inverseVertexTable.size(), from);
        }
        if (!vertexTable.containsKey(to)) {
            vertexTable.put(to, vertexTable.size());
            inverseVertexTable.put(inverseVertexTable.size(), to);
        }

        int fromIndex = vertexTable.get(from);
        int toIndex = vertexTable.get(to);

        // 如果链表数组的长度不够长，则需要扩容
        int maxIndex = Math.max(fromIndex, toIndex);
        while (data.size() <= maxIndex) {
            data.add(new LinkedList<>());
        }

        // 填充数据
        data.get(fromIndex).add(toIndex);
    }

    // 添加一条带有权值的边
    public void addEdge(T from, T to, int weight) {
        addEdge(from, to);
        weightTable.put(new Edge(getIndex(from), getIndex(to)), weight);
    }

    public void addUndirectedEdge(T from, T to) {
        addEdge(from, to);
        addEdge(to, from);
    }

    public void addUndirectedEdge(T from, T to, int weight) {
        addEdge(from, to, weight);
        addEdge(to, from, weight);
    }

    // 通过结点的编号，返回结点的引用
    public T getNode(int key) {
        return inverseVertexTable.getOrDefault(key, null);
    }

    // 通过结点的引用，返回结点的编号
    public int getIndex(T node) {
        return vertexTable.getOrDefault(node, -1);
    }

    // 返回第i个结点的边链表
    public LinkedList<Integer> getLinkedEdge(int index) {
        return data.get(index);
    }

    // 返回结点个数
    public int getVertexNumber() {
        return data.size();
    }

    // 获取权值,default value is integer.max_value
    public int getWeight(T from, T to) {
        return getWeight(getIndex(from), getIndex(to));
    }

    public int getWeight(int from, int to) {
        return weightTable.getOrDefault(new Edge(from, to), Integer.MAX_VALUE);
    }

    public ArrayList<LinkedList<Integer>> getLinkedTable() {
        return data;
    }


    @Override
    public String toString() {
        return "Graph{" +
                "inverseVertexTable=" + inverseVertexTable +
                ", data=" + data +
                '}';
    }
}
