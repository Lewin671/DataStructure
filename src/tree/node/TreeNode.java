package tree.node;

/**
 * 二叉链式树结点
 *
 * @param <T> 关键字的类型
 */
public class TreeNode<T> {

    protected T key;
    // 此结点的左右孩子
    protected TreeNode<T> left, right;

    public TreeNode(T key) {
        this.key = key;
        left = right = null;
    }

    public TreeNode() {
        this(null);
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }


    public TreeNode<T> getLeft() {
        return left;
    }


    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }


    public TreeNode<T> getRight() {
        return right;
    }


    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
