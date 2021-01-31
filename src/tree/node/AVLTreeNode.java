package tree.node;

/**
 * AVL树的结点
 *
 * @param <T> 关键字的类型
 */
public class AVLTreeNode<T> extends TreeNode<T> {
    // 树的高度，方便计算balance factor
    private int height;

    public AVLTreeNode(T key) {
        super(key);
        this.height = 1;
    }

    @Override
    public AVLTreeNode<T> getLeft() {
        return (AVLTreeNode<T>) super.getLeft();
    }

    @Override
    public AVLTreeNode<T> getRight() {
        return (AVLTreeNode<T>) super.getRight();
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
            AVLTreeNode<T> left = getLeft();
            return left.height;
        }
    }

    // 右孩子的高度
    public int getRightHeight() {
        if (right == null) {
            return 0;
        } else {
            AVLTreeNode<T> right = getRight();
            return right.height;
        }
    }

    public void updateHeight() {
        this.height = Math.max(getLeftHeight(), getRightHeight()) + 1;
    }

    @Override
    public String toString() {
        return key.toString() + "(bf: " + getBf() + ")";
    }
}
