package tree.bitree;

import tree.node.AVLTreeNode;
import tree.node.TreeNode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// AVL树的实现

public class AVLTree<T> extends BinarySearchTree<T> {
    private AVLTreeNode<T> treeRoot;

    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    public AVLTree(TreeNode<T> treeRoot, Comparator<T> comparator) {
        super(treeRoot, comparator);
    }

    // 插入一个结点
    public void insert(AVLTreeNode<T> element) {
        if (treeRoot == null) {
            treeRoot = element;
        } else {
            LinkedList<AVLTreeNode<T>> path = new LinkedList<>(); //记录从根节点到插入位置的路径
            LinkedList<Boolean> isLeftList = new LinkedList<>();

            AVLTreeNode<T> q = treeRoot;
            AVLTreeNode<T> p = null; // parent of q
            while (q != null) {
                path.addFirst(q);
                p = q;
                if (c.compare(q.getKey(), element.getKey()) > 0) {
                    q = q.getLeft();
                    isLeftList.addFirst(true);
                } else {
                    q = q.getRight();
                    isLeftList.addFirst(false);
                }
            }

            // 插入操作
            // 由于排除了treeRoot == null的操作，于是p!=null
            if (isLeftList.get(0)) {
                p.setLeft(element);
            } else {
                p.setRight(element);
            }

            int k = -1;
            // 更新balance factor
            for (int i = 0; i < isLeftList.size(); i++) {
                AVLTreeNode<T> node = path.get(i);
                if (isLeftList.get(i)) {
                    node.getLeft().increaseHeight();
                } else {
                    node.getRight().increaseHeight();
                }

                if (node.getBf() > 1 || node.getBf() < -1) {
                    k = i;
                    break;
                }

                // 在这条路径上出现bf = 0，则说明插入结点不改变此树的高度
                if (node.getBf() == 0) {
                    break;
                }
            }

            // 发现不平衡的结点，其下标为k，那么其父节点下标为k+1
            if (k != -1) {
                AVLTreeNode<T> unbalanceNode = path.get(k);
                AVLTreeNode<T> parent = null;
                boolean isLeftRelation = false;
                // 得到不平衡结点的父亲和它们之间的关系（是左孩子还是右孩子?）
                if (k + 1 < path.size()) {
                    parent = path.get(k + 1);
                    isLeftRelation = isLeftList.get(k + 1);
                }

                if (isLeftList.get(k) && isLeftList.get(k - 1)) {
                    //System.out.println(unbalanceNode + "LL旋转");
                    LLRotate(unbalanceNode, parent,isLeftRelation);
                } else if (!isLeftList.get(k) && !isLeftList.get(k - 1)) {
                    //System.out.println(unbalanceNode + "RR旋转");
                    RRRotate(unbalanceNode, parent,isLeftRelation);
                } else if (isLeftList.get(k) && !isLeftList.get(k - 1)) {
                    //System.out.println(unbalanceNode + "LR旋转");
                    LRRotate(unbalanceNode, parent,isLeftRelation);
                } else {
                    //System.out.println(unbalanceNode + "RL旋转");
                    RLRotate(unbalanceNode, parent,isLeftRelation);
                }
            }
        }
    }

    public void remove(T key) {

    }

    /**
     * LL rotate
     * @param node the unbalanced node
     * @param parent the parent of the unbalance node
     * @param isLeft whether the node is the left child of its parent
     */
    private void LLRotate(AVLTreeNode<T> node, AVLTreeNode<T> parent,boolean isLeft) {
        AVLTreeNode<T> newRoot = node.getLeft();
        // 此时node就是根结点
        if (parent == null) {
            treeRoot = newRoot;
            node.setLeft(newRoot.getRight());
            treeRoot.setRight(node);
        } else {
            // 判断node是parent的左子树还是右子树
            // 注意，这里不能简单地根据parent的key值和node的key值来判断
            if (isLeft) { // 左子树
                //parent.left = newRoot;
                parent.setLeft(newRoot);
            } else {
                //parent.right = newRoot;
                parent.setRight(newRoot);
            }
            //node.left = newRoot.right;
            node.setLeft(newRoot.getRight());
            //newRoot.right = node;
            newRoot.setRight(node);
        }

        // 更新高度
        node.setHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
        newRoot.setHeight(Math.max(newRoot.getLeftHeight(), newRoot.getRightHeight()) + 1);
    }

    private void RRRotate(AVLTreeNode<T> node, AVLTreeNode<T> parent,boolean isLeft) {
        AVLTreeNode<T> newRoot = node.getRight();
        // 此时node就是根结点
        if (parent == null) {
            treeRoot = newRoot;
            //node.right = newRoot.getLeft();
            node.setRight(newRoot.getLeft());
            //treeRoot.left = node;
            treeRoot.setLeft(node);
        } else {
            // 判断node是parent的左子树还是右子树
            if (isLeft) { // 左子树
                //parent.left = newRoot;
                parent.setLeft(newRoot);
            } else {
                //parent.right = newRoot;
                parent.setRight(newRoot);
            }
            //node.right = newRoot.left;
            node.setRight(newRoot.getLeft());
            //newRoot.left = node;
            newRoot.setLeft(node);
        }

        // 更新高度
        node.setHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
        newRoot.setHeight(Math.max(newRoot.getLeftHeight(), newRoot.getRightHeight()) + 1);
    }

    private void LRRotate(AVLTreeNode<T> node, AVLTreeNode<T> parent,boolean isLeft) {
        RRRotate(node.getLeft(), node,true);
        LLRotate(node, parent,isLeft);
    }

    private void RLRotate(AVLTreeNode<T> node, AVLTreeNode<T> parent,boolean isLeft) {
        LLRotate(node.getRight(), node,false);
        RRRotate(node, parent,isLeft);
    }

    public AVLTreeNode<T> getTreeRoot() {
        return treeRoot;
    }

    private boolean verifyAVLTree(AVLTreeNode<T> root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(root.getLeftHeight() - root.getRightHeight()) <= 1
                    && verifyAVLTree(root.getLeft()) && verifyAVLTree(root.getRight());
        }
    }

    public boolean verifyAVLTree() {
        return verifyAVLTree(this.treeRoot);
    }

    @Override
    public AVLTreeNode<T> getRoot() {
        return treeRoot;
    }

    public List<T> inOrder() {
        return super.inOrder();
    }

}
