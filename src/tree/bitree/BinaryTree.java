package tree.bitree;

import tree.node.LinkedTreeNode;

import java.util.*;

/**
 * 二叉树类，这里是用链式存储来实现的
 * 主要实现了二叉树的遍历，包括前序、中序和后序遍历
 * 同时也实现了二叉树的可视化toString，可以极大地方便调试AVL树的测试
 *
 * @param <T> 关键字的类型
 */
public class BinaryTree<T> {
    protected LinkedTreeNode<T> treeRoot; // 需要继承给BinarySearchTree

    public BinaryTree(LinkedTreeNode<T> root) {
        treeRoot = root;
    }

    public BinaryTree() {
        treeRoot = null;
    }

    public static <T> void traversePreOrder(StringBuilder sb, String padding, String pointer, LinkedTreeNode<T> node, boolean hasRightSibling) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.toString());
            sb.append("\n");

            if (node.getLeft() == null && node.getRight() == null) {
                return;
            }

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if(hasRightSibling) {
                paddingBuilder.append("│  ");
            }else{
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.getRight() != null) ? "├──" : "└──";

            traversePreOrder(sb, paddingForBoth, pointerForLeft, node.getLeft(),true);
            traversePreOrder(sb, paddingForBoth, pointerForRight, node.getRight(), false);
        }else {
            sb.append(padding);
            sb.append(pointer);
            sb.append("null");
            sb.append("\n");
        }
    }

    public List<T> preOrder() {
        return preOrder(treeRoot);
    }

    // 非递归先序遍历，尾递归优化
    protected List<T> preOrder(LinkedTreeNode<T> root) {
        ArrayList<T> res = new ArrayList<>();
        // 如果树为空
        if (root == null) return res;

        // 如果树不为空
        Deque<LinkedTreeNode<T>> stack = new ArrayDeque<>();
        // 初始化
        stack.push(root);
        // 迭代
        // 注意stack有reverse的效果

        while (!stack.isEmpty()) {
            LinkedTreeNode<T> cur = stack.pop();
            res.add(cur.getKey());
            if (cur.getRight() != null) {
                stack.push(cur.getRight());
            }
            if (cur.getLeft() != null) {
                stack.push(cur.getLeft());
            }
        }

        return res;
    }

    public List<T> inOrder(){
        return inOrder(getRoot());
    }

    // 非递归中序遍历
    protected List<T> inOrder(LinkedTreeNode<T> root) {
        ArrayList<T> res = new ArrayList<>();
        // 如果树为空
        if (root == null) return res;

        // 如果树不为空
        Deque<LinkedTreeNode<T>> stack = new ArrayDeque<>();

        // 迭代
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            res.add(root.getKey());
            root = root.getRight();
        }
        return res;
    }

    // 非递归后续遍历
    protected List<T> postOrder(LinkedTreeNode<T> root) {
        LinkedList<T> res = new LinkedList<>();
        // 如果树为空
        if (root == null) return res;

        // 如果树不为空
        Deque<LinkedTreeNode<T>> stack = new ArrayDeque<>();
        // 初始化
        LinkedTreeNode<T> cur;
        stack.push(root);

        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.addFirst(cur.getKey());
            if (cur.getLeft() != null) {
                stack.push(cur.getLeft());
            }
            if (cur.getRight() != null) {
                stack.push(cur.getRight());
            }
        }
        return res;
    }

    public List<T> postOrder() {
        // return postOrderCountVersion(treeRoot);
        return postOrder(treeRoot);
    }

    private List<T> postOrderCountVersion(LinkedTreeNode<T> root) {
        class Pair<K, V> {
            final K first;
            final V second;


            Pair(K first, V second) {
                this.first = first;
                this.second = second;
            }
        }
        List<T> res = new LinkedList<>();
        if (root == null) return res;
        Deque<Pair<LinkedTreeNode<T>, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));

        while (!stack.isEmpty()) {
            Pair<LinkedTreeNode<T>, Integer> cur = stack.pop();
            if (cur.second == 0) { // 开始访问左子树
                stack.push(new Pair<>(cur.first, 1));
                if (cur.first.getLeft() != null) {
                    stack.push(new Pair<>(cur.first.getLeft(), 0));
                }
            } else if (cur.second == 1) { // 访问完了左子树，开始访问右子树
                stack.push(new Pair<>(cur.first, 2));
                if (cur.first.getRight() != null) {
                    stack.push(new Pair<>(cur.first.getRight(), 0));
                }
            } else { // cur.second == 2,此时访问完了右子树
                res.add(cur.first.getKey()); // 访问根结点
            }
        }
        return res;
    }

    public BinaryTree<T> deepClone() {
        BinaryTree<T> binaryTree = new BinaryTree<>();
        binaryTree.treeRoot = deepCloneHelper(treeRoot);
        return binaryTree;
    }

    private LinkedTreeNode<T> deepCloneHelper(LinkedTreeNode<T> root) {
        if (root == null) return null;
        LinkedTreeNode<T> newRoot = new LinkedTreeNode<>();
        newRoot.setKey(root.getKey());
        newRoot.setLeft(deepCloneHelper(root.getLeft()));
        newRoot.setRight(deepCloneHelper(root.getRight()));
        return newRoot;
    }

    @Override
    public String toString() {
        if(getRoot() == null){
            return "";
        }
        String pointerForLeft = getRoot().getRight() == null ? "└──" : "├──";
        String pointerForRight = "└──";

        StringBuilder sb = new StringBuilder();
        sb.append(getRoot().toString());
        sb.append("\n");

        traversePreOrder(sb,"",pointerForLeft,getRoot().getLeft(),true);
        traversePreOrder(sb,"",pointerForRight,getRoot().getRight(),false);
        return sb.toString();
    }

    public LinkedTreeNode<T> getRoot() {
        return treeRoot;
    }
}
