package tree.node;

/**
 * 二叉链式树结点
 *
 * @param <T> 关键字的类型
 */
public class LinkedTreeNode<T> extends TreeNode<T> {

    // 此结点的左右孩子
    protected LinkedTreeNode<T> left, right;

    public LinkedTreeNode(T key) {
        this.key = key;
        left = right = null;
    }

    public LinkedTreeNode() {
        left = right = null;
        key = null;
    }

    @Override
    public LinkedTreeNode<T> getLeft() {
        return left;
    }

    @Override
    public void setLeft(TreeNode<T> left) {
        if (left == null) {
            this.left = null;
            return;
        }

        // 此时left不为null，对它进行类型检查
        if (left.getClass() == this.getClass()) {
            this.left = (LinkedTreeNode<T>) left;
        } else {
            throw new RuntimeException("TreeNode type is illegal.");
        }
    }

    @Override
    public LinkedTreeNode<T> getRight() {
        return right;
    }

    @Override
    public void setRight(TreeNode<T> right) {
        if (right == null) {
            this.right = null;
            return;
        }

        // 同setLeft，需要检查强制转换的合法性
        if (right.getClass() == this.getClass()) {
            this.right = (LinkedTreeNode<T>) right;
        } else {
            throw new RuntimeException("TreeNode type is illegal.");
        }
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
