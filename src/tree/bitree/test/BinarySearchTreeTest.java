package tree.bitree.test;

import tree.bitree.BinarySearchTree;
import tree.node.TreeNode;

import java.util.Scanner;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinarySearchTree<Integer> s = new BinarySearchTree<>(Integer::compareTo);

        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            s.insert(new TreeNode<>(in.nextInt()));
            System.out.println(s);
        }

        System.out.println(s.inOrder());

        in.close();
    }
}
