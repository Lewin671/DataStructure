package tree.node;

/**
 * 树结点抽象类，其中key值保存树的关键字
 *
 * @param <T> 树结点的类型
 */
public abstract class TreeNode<T> {
    protected T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public abstract TreeNode<T> getLeft();

    public abstract void setLeft(TreeNode<T> left);

    public abstract TreeNode<T> getRight();

    public abstract void setRight(TreeNode<T> right);
}
