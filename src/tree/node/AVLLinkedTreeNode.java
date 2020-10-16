package tree.node;

/**
 * AVL树的结点
 *
 * @param <T> 关键字的类型
 */
public class AVLLinkedTreeNode<T> extends LinkedTreeNode<T> {
    // 树的高度，方便计算balance factor
    private int height;

    public AVLLinkedTreeNode(T key) {
        this.key = key;
        left = right = null;
        this.height = 1;
    }

    // 计算balance factor
    public int getBf() {
        return getRightHeight() - getLeftHeight();
    }

    // 获取左孩子的高度
    public int getLeftHeight() {
        if (left == null) {
            return 0;
        } else {
            return getLeft().height;
        }
    }

    // 右孩子的高度
    public int getRightHeight() {
        if (right == null) {
            return 0;
        } else {
            return getRight().height;
        }
    }

    @Override
    public AVLLinkedTreeNode<T> getLeft() {
        return (AVLLinkedTreeNode<T>) left;
    }

    @Override
    public AVLLinkedTreeNode<T> getRight() {
        return (AVLLinkedTreeNode<T>) right;
    }

    public void updateHeight() {
        this.height = Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    @Override
    public String toString() {
        return key.toString() + "(bf: " + getBf() + ")";
    }
}
