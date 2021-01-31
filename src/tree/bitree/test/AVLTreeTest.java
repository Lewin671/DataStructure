package tree.bitree.test;

import tree.bitree.AVLTree;
import tree.node.AVLTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AVLTreeTest {
    public static List<List<Integer>> getPermutation(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            res.add(list);
        } else {
            List<List<Integer>> subPermutation = getPermutation(n - 1);
            for (List<Integer> list : subPermutation) {
                // 插入到第i个位置
                for (int i = n - 1; i >= 0; i--) {
                    List<Integer> itemList = new ArrayList<>();

                    for (int j = 0; j < n - 1; j++) {
                        if (j == i) {
                            itemList.add(n);
                        }
                        itemList.add(list.get(j));
                    }

                    if (i == n - 1) {
                        itemList.add(n);
                    }

                    res.add(itemList);
                }
            }
        }
        return res;
    }

    static void test(int n, List<Integer> inputList, AVLTree<Integer> tree) {
        for (int i = 0; i < n; i++) {
            int item = inputList.get(i);//in.nextInt();//
            tree.insert(new AVLTreeNode<>(item));
            if (tree.verifyAVLTree()) {
                System.out.println(inputList);
                System.out.println(tree);
                throw new RuntimeException("插入操作不符合AVL树的定义");
                //System.out.println("不符合AVL树的定义");
            }
            //System.out.println(tree);
        }

        // System.out.println(tree);
        // System.out.println(tree.inOrder());

        for (int i = 0; i < n; i++) {
            tree.remove(inputList.get(i));
            if (tree.verifyAVLTree()) {
                System.out.println(inputList);
                System.out.println(tree);
                throw new RuntimeException("删除操作不符合AVL树的定义");
                //System.out.println("不符合AVL树的定义");
            }
            //System.out.println(tree);
        }
    }

    static List<List<Integer>> randomList(int m, int n) {
        List<List<Integer>> res = new ArrayList<>();

        Random random = new Random();
        int bound = 1000;

        for (int i = 0; i < m; i++) {
            List<Integer> itemList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                itemList.add(random.nextInt(bound));
            }
            res.add(itemList);
        }
        return res;
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>(Integer::compareTo);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        //List<List<Integer>> testDataList = getPermutation(n);
        /*
        List<List<Integer>> testDataList = randomList(1000, n);

        System.out.println("数据生成完毕，开始测试");

        for (List<Integer> inputList : testDataList) {
            test(n, inputList, new AVLTree<>(Integer::compareTo));
       }
         */


        System.out.println("开始人工测试");
        for (int i = 0; i < n; i++) {
            tree.insert(new AVLTreeNode<>(in.nextInt()));
        }

        System.out.println("插入完成的情况\n" + tree);

        for (int i = 0; i < n; i++) {
            tree.remove(in.nextInt());
            if (tree.verifyAVLTree()) {
                System.out.println("删除算法有误");
                break;
            }
            System.out.println(tree);
        }

        System.out.println(tree);
        System.out.println(tree.inOrder());

        in.close();
    }
}
