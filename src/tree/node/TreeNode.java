package tree.node;

public class TreeNode<T> {
    protected T key; // 需要给子类继承
    private TreeNode<T> left, right;

    public TreeNode(T key) {
        this.key = key;
        left = right = null;
    }

    public TreeNode() {
        left = right = null;
        key = null;
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
