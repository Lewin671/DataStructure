package graph.test;

import graph.Graph;
import graph.TopoSort;

import java.util.Scanner;

public class TopoSortTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph<String> graph = new Graph<>();
        int n = in.nextInt(); //n条边
        String from;
        String to;
        for (int i = 0; i < n; i++) {
            from = in.next();
            to = in.next();
            graph.addEdge(from, to);
        }

        // 开始测试
        TopoSort<String> topoSort = new TopoSort<>(graph);
        System.out.println(topoSort.topoSortWithItem());

        in.close();
    }
}
/*
test data:
10
数据结构 操作系统
算法设计 操作系统
C语言 算法设计
C++ 算法设计
数据结构 算法设计
C语言 C++
C语言 数据结构
C++ 数据结构
C语言 操作系统
C++ 操作系统
*/