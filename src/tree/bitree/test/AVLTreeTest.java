package tree.bitree.test;

import tree.bitree.AVLTree;
import tree.node.AVLTreeNode;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>(Integer::compareTo);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Random random = new Random();
        ArrayList<Integer> inputList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int item = random.nextInt(50000); //in.nextInt();//
            inputList.add(item);
            tree.insert(new AVLTreeNode<>(item));
            if(!tree.verifyAVLTree()){
                System.out.println("算法错误");
            }
            System.out.println(tree);
        }

        //System.out.println(inputList);
        System.out.println("中序遍历的结点个数: "+tree.inOrder().size());
        System.out.println(tree.inOrder());



        in.close();
    }
}
