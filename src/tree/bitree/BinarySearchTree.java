package tree.bitree;

import tree.node.LinkedTreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 二叉查找树(二叉搜索树)
 * 主要包括三个操作: 插入、删除、查找
 * 对于任意一个结点p，其左子树的全部结点小于key(p)，右子树的结点都大于key(p)
 * 不可以可以包含重复的key值
 *
 * @param <T>
 */
public class BinarySearchTree<T> extends BinaryTree<T> {
    protected Comparator<T> c; // 给AVL树继承

    public BinarySearchTree(Comparator<T> comparator) {
        super();
        this.c = comparator;
    }

    public BinarySearchTree(LinkedTreeNode<T> treeRoot, Comparator<T> comparator) {
        this.treeRoot = treeRoot;
        this.c = comparator;
    }

    public static BinarySearchTree<Integer> getRandomTree(int n){
        Comparator<Integer> c = Integer::compareTo;
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(c);

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            tree.insert(new LinkedTreeNode<>(random.nextInt(10000)));
        }

        return tree;
    }

    /**
     * insert a treeNode into the binary search tree
     *
     * @param element 插入到二叉搜索树的树结点
     */
    public void insert(LinkedTreeNode<T> element) {
        if (treeRoot == null) {
            treeRoot = element;
        } else {
            if (search(element.getKey()) != null) {
                return;
            }

            LinkedTreeNode<T> root = treeRoot;
            while (true) {
                if (c.compare(root.getKey(), element.getKey()) > 0) {
                    if (root.getLeft() == null) {
                        root.setLeft(element);
                        break;
                    } else {
                        root = root.getLeft();
                    }
                } else {
                    if (root.getRight() == null) {
                        root.setRight(element);
                        break;
                    } else {
                        root = root.getRight();
                    }
                }
            }
        }
    }

    /**
     * remove the treeNode by its key
     *
     * @param key the key of a treeNode which will be removed
     *            if there are many same keys, remove any of their corresponding treeNode
     */
    public void remove(T key) {
        LinkedTreeNode<T> p, parent;
        List<LinkedTreeNode<T>> searchResult = searchNodeAndParent(key);
        p = searchResult.get(0);
        parent = searchResult.get(1);

        // 不存在包含key的结点,删除无效
        if (p == null) {
            throw new RuntimeException("the key doesn't exist in BinarySearchTree");
        }

        // 此时必定存在p在树中，此时用p的中序后继来替代p
        // 1. p的右子树为空
        // 2. p的右子树不为空，且右子树存在左孩子
        // 3. p的右子树不为空，且右子树不存左孩子

        LinkedTreeNode<T> successor; //找p的继承结点
        LinkedTreeNode<T> parentOfSuccessor; // successor的父亲结点，注意: successor是左孩子

        if (p.getRight() == null) { // 如果p的右子树为空，那么用p的左子树代替p
            successor = p.getLeft();
        } else { // 否则找p的中序后继
            successor = p.getRight();
            parentOfSuccessor = p;

            while (successor.getLeft() != null) {
                parentOfSuccessor = successor;
                successor = successor.getLeft();
            }

            // p的存在右孩子，并且右孩子存在非空左子树
            if (p.getRight().getLeft() != null) {
                parentOfSuccessor.setLeft(null);
            } else { // p的存在右孩子，并且右孩子没有左子树
                parentOfSuccessor.setRight(successor.getRight());
            }

            successor.setLeft(p.getLeft());
            successor.setRight(p.getRight());
        }

        // 此时只可能是根结点
        if (parent == null) {
            treeRoot = successor;
        } else {
            // 此时p是parent的左子树
            if (c.compare(parent.getKey(), p.getKey()) > 0) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
        }
    }

    /**
     * to find the corresponding treeNode by a key
     *
     * @param key 查找的关键字
     * @return a treeNode whose key is equal to the given key
     */
    public LinkedTreeNode<T> search(T key) {
        LinkedTreeNode<T> root = treeRoot;

        while (root != null) {
            if (c.compare(root.getKey(), key) > 0) {
                root = root.getLeft();
            } else if (c.compare(root.getKey(), key) < 0) {
                root = root.getRight();
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * find the parent of treeNode of the given key, we assume the key exists in the tree
     *
     * @param key the given key
     * @return [node, its parent]
     */
    public List<LinkedTreeNode<T>> searchNodeAndParent(T key) {
        ArrayList<LinkedTreeNode<T>> res = new ArrayList<>();
        // 如果树为空，或者根的关键字就等于key，那么就返回null
        if (treeRoot == null) {
            res.add(null);
            res.add(null);
            return res;
        }

        if (c.compare(treeRoot.getKey(), key) == 0) {
            res.add(treeRoot);
            res.add(null);
            return res;
        }

        LinkedTreeNode<T> root = treeRoot;
        LinkedTreeNode<T> leftChild, rightChild;

        while (root != null) {
            leftChild = root.getLeft();
            rightChild = root.getRight();

            if (c.compare(root.getKey(), key) > 0) {
                if (leftChild != null && c.compare(leftChild.getKey(), key) == 0) {
                    res.add(root.getLeft());
                    res.add(root);
                    return res;
                }
                root = root.getLeft();
            } else {
                if (rightChild != null && c.compare(rightChild.getKey(), key) == 0) {
                    res.add(root.getRight());
                    res.add(root);
                    return res;
                }
                root = root.getRight();
            }
        }
        // 该节点不存在在树中
        res.add(null);
        res.add(null);
        return res;
    }
}
