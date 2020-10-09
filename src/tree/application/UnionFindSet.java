package tree.application;

/* 并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的合并及查询问题。
它支持两种操作：
    查找（Find）：确定某个元素处于哪个子集；
    合并（Union）：将两个子集合并成一个集合。
*/

public class UnionFindSet {
    private int[] father; // 记录父亲结点的树
    private int[] rank; // 记录树的秩(树的高度的上界)
    private int n;


    // 给定最多是多少结点
    public UnionFindSet(int _n) {
        n = _n;
        father = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            rank[i] = 0;
        }
    }

    // 按秩合并
    public void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (rank[fx] > rank[fy]) { // 确保rank[fx] <= rank[fy]
            int tmp = fx;
            fx = fy;
            fy = tmp;
        }

        if (rank[fx] == rank[fy]) {
            rank[fy]++;
        }

        father[fx] = fy;
    }

    // 查找的过程中进行路径压缩
    public int find(int x) {
        if (father[x] == x) {
            return x;
        } else {
            return father[x] = find(father[x]);
        }
    }

}
