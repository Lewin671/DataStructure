package tree.bitree;

import tree.node.AVLTreeNode;

import java.util.Comparator;


/**
 * AVL树
 * 主要实现了插入、删除功能，同时维持了AVL树的性质
 * 不支持key值的重复
 *
 * @param <T> 关键字key的类型
 */
public class AVLTree<T> extends BinarySearchTree<T> {
    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    public AVLTree(AVLTreeNode<T> treeRoot, Comparator<T> comparator) {
        super(treeRoot, comparator);
    }

    /**
     * insert a AVLTreeNode.
     * if there exists an the same key element, we will not insert again.
     *
     * @param element The element that will inserted into the tree
     */
    public void insert(AVLTreeNode<T> element) {
        // don't support to insert the same key node
        if (search(element.getKey()) == null)
            treeRoot = insert(getRoot(), element);
    }

    /**
     * insert an element node recursively, remaining the balance of AVLTree
     *
     * @param root    the AVLTree root
     * @param element The element that will inserted into the tree
     * @return an new root with inserted element
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> root, AVLTreeNode<T> element) {
        // 如果元素为空，则不插入
        if (element == null) {
            return root;
        }

        // 如果当前的根为空，则用当前元素看做是根
        if (root == null) {
            return element;
        }

        int cmp = c.compare(element.getKey(), root.getKey());

        if (cmp > 0) {
            AVLTreeNode<T> rightChild = root.getRight();
            // 如果没有右孩子了，那么就只能直接插入了
            if (rightChild == null) {
                root.setRight(element);
            } else {
                root.setRight(insert(rightChild, element));
            }
        } else {
            AVLTreeNode<T> leftChild = root.getLeft();
            if (leftChild == null) {
                root.setLeft(element);
            } else {
                root.setLeft(insert(leftChild, element));
            }
        }

        // 更新高度
        root.updateHeight();

        // 对树进行平衡
        return balance(root);
    }

    // 删除一个结点

    /**
     * remove an AVLTreeNode by an key.
     * We do not allow to remove an nonexistent key, which will cause the program throws an exception.
     *
     * @param key the key of a treeNode which will be removed
     */
    public void remove(T key) {
        // 确保被删除的元素是存在的
        if (search(key) == null) {
            throw new RuntimeException("the key doesn't exist in AVLTree");
            // 测试的时候就直接返回
            // System.out.println("元素不在AVL树中，直接返回");
            // return;
        }

        // update new tree root
        treeRoot = remove(getRoot(), key);
    }

    /**
     * 给公共方法remove调用的递归函数
     *
     * @param root 这里的root需要确保不为null
     */
    private AVLTreeNode<T> remove(AVLTreeNode<T> root, T key) {
        AVLTreeNode<T> res;

        int cmp = c.compare(key, root.getKey());
        if (cmp < 0) { // 在左子树上删除
            root.setLeft(remove(root.getLeft(), key));
            res = root;
        } else if (cmp > 0) { // 在右子树上删除
            root.setRight(remove(root.getRight(), key));
            res = root;
        } else {
            // root的右孩子
            final AVLTreeNode<T> rightChild = root.getRight();

            // 如果右子树为空,则用其左子树的第一个结点来代替root
            if (rightChild == null) {
                res = root.getLeft();
            } else { // 如果右子树不为空
                // root的右孩子的左孩子
                AVLTreeNode<T> subLeftChild = rightChild.getLeft();

                if (subLeftChild == null) {
                    // 用rightChild代替root
                    res = rightChild;
                    // 将root的左孩子给rightChild，右孩子保留原状
                    rightChild.setLeft(root.getLeft());
                } else {
                    // 用中序后继来替代root
                    // 此时的中序后继在右孩子的左子树上
                    AVLTreeNode<T> successor = rightChild;
                    while (successor.getLeft() != null) {
                        successor = successor.getLeft();
                    }

                    // 注意,successor的改动会影响后续的结果
                    AVLTreeNode<T> newRightChild = remove(rightChild, successor.getKey());
                    successor.setRight(newRightChild);
                    successor.setLeft(root.getLeft());

                    res = successor;
                }
            }
        }

        // 更新高度
        if (res != null) {
            res.updateHeight();
            res = balance(res);
        }

        return res;
    }


    /**
     * verify an tree with root whether is an AVLTree
     *
     * @param root the root of an AVLTree
     * @return true if the root is an AVLTree. Otherwise, return false
     */
    private boolean verifyAVLTree(AVLTreeNode<T> root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(root.getLeftHeight() - root.getRightHeight()) <= 1
                    && verifyAVLTree(root.getLeft()) && verifyAVLTree(root.getRight());
        }
    }

    public boolean verifyAVLTree() {
        return !verifyAVLTree(getRoot());
    }

    @Override
    public AVLTreeNode<T> getRoot() {
        return (AVLTreeNode<T>) treeRoot;
    }


    private AVLTreeNode<T> LLRotate(AVLTreeNode<T> node) {
        AVLTreeNode<T> res = node.getLeft();
        AVLTreeNode<T> r = res.getRight();
        // 向右旋转
        node.setLeft(r);
        res.setRight(node);

        // 更新height,这个顺序不能反过来!!!
        node.updateHeight();
        res.updateHeight();
        return res;
    }

    private AVLTreeNode<T> RRRotate(AVLTreeNode<T> node) {
        AVLTreeNode<T> res = node.getRight();
        AVLTreeNode<T> l = res.getLeft();
        // 向右旋转
        node.setRight(l);
        res.setLeft(node);

        // 更新height
        node.updateHeight();
        res.updateHeight();
        return res;
    }

    private AVLTreeNode<T> LRRotate(AVLTreeNode<T> node) {
        // 先对node.left进行RR旋转
        node.setLeft(RRRotate(node.getLeft()));
        node.updateHeight();
        // 再对node进行LL旋转
        return LLRotate(node);
    }

    private AVLTreeNode<T> RLRotate(AVLTreeNode<T> node) {
        // 先对node.right进行LL旋转
        node.setRight(LLRotate(node.getRight()));
        node.updateHeight();
        return RRRotate(node);
    }

    /**
     * according to the offsprings of the node to balance the node
     *
     * @param node a node may be balanced
     * @return a new balanced node root
     */
    private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
        int bf = node.getBf();
        if (bf > 1) {
            AVLTreeNode<T> rightChild = node.getRight();
            // 注意等号的情况
            if (rightChild.getLeftHeight() <= rightChild.getRightHeight()) {
                //System.out.println("RR旋转");
                return RRRotate(node);
            } else {
                //System.out.println("RL旋转");
                return RLRotate(node);
            }
        }

        if (bf < -1) {
            AVLTreeNode<T> leftChild = node.getLeft();
            if (leftChild.getLeftHeight() < leftChild.getRightHeight()) {
                //System.out.println("LR旋转");
                return LRRotate(node);
            } else {
                //System.out.println("LL旋转");
                return LLRotate(node);
            }
        }

        return node;
    }

}
