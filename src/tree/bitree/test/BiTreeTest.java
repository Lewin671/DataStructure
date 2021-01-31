package tree.bitree.test;

import tree.bitree.BinaryTree;
import tree.node.TreeNode;

import java.util.Scanner;

// 以int类型来测试
public class BiTreeTest {
    Scanner in;

    BiTreeTest() {
        in = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // input1: 4 2 0 3 0 0 1 0 0
        // input2: 1 2 4 0 0 5 9 0 0 0 3 6 0 0 7 0 0
        BiTreeTest biTreeTest = new BiTreeTest();
        biTreeTest.testTraversal();
        //biTreeTest.deepCloneTest();
        biTreeTest.in.close();

    }

    // 中序遍历建立二叉树
    // 0表示null
    TreeNode<Integer> buildTree() {
        int item = in.nextInt();
        if (item == 0) {
            return null;
        }
        TreeNode<Integer> root = new TreeNode<>();
        root.setKey(item);
        root.setLeft(buildTree());
        root.setRight(buildTree());
        return root;
    }

    void testTraversal() {
        TreeNode<Integer> tree = buildTree();
        BinaryTree<Integer> biTree = new BinaryTree<>(tree);

        System.out.println(biTree.preOrder());
        System.out.println(biTree.inOrder());
        System.out.println(biTree.postOrder());
    }

    void deepCloneTest() {
        TreeNode<Integer> tree = buildTree();
        BinaryTree<Integer> biTree = new BinaryTree<>(tree);

        BinaryTree<Integer> clonedTree = biTree.deepClone();

        System.out.println("origin tree: " + biTree.preOrder());
        // change the origin tree
        System.out.println("请输入一个item修改root值");
        tree.setKey(in.nextInt());
        System.out.println("changed origin tree: " + biTree.preOrder());
        System.out.println("deep cloned tree: " + clonedTree.preOrder());
    }
}
