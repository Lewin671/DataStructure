package tree.node;

public class AVLTreeNode<T> extends TreeNode<T> {
    private int height;
    private AVLTreeNode<T> left, right;

    public AVLTreeNode(T key) {
        this.key = key;
        left = right = null;
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBf() {
        return getRightHeight() - getLeftHeight();
    }

    public int getLeftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height;
        }
    }

    public int getRightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height;
        }
    }

    public void increaseHeight() {
        height++;
    }

    public void descreaseHeight() {
        height--;
    }

    public AVLTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode<T> left) {
        this.left = left;
    }

    public AVLTreeNode<T> getRight() {
        return right;
    }

    public void setRight(AVLTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return key.toString() + "(bf: "+ getBf() +")";
    }
}
