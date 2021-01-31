### 树

### application

- 表达式树的应用: `ExpressionTree`
- 并查集: 'UnionFindSet'

### binary tree

- `BinaryTree`: 主要实现了二叉树的通用函数，比如二叉树的非递归遍历、二叉树的树状可视化，方便后续的二分查找树和AVL树的调试
- `BinarySearchTree`: 二叉搜索树，主要实现了插入、删除和查找功能
- `AVLTree`： 特殊的二叉搜索树，继承了二叉搜索树的功能。但是对插入和删除的操作进行了重载，使得每次操作都能保持AVL树的性质

一个简单的AVL树的测试结果如下:

插入: `6 3 1 7 8`

```
3(bf: 1)
├──1(bf: 0)
└──7(bf: 0)
   ├──6(bf: 0)
   └──8(bf: 0)
```

其中bf记录每个结点的balance factor.

### node

普通链式二叉节点: `TreeNode`
AVL树节点: `AVLTreeNode`，在`TreeNode`的基础上添加了`height`属性，并添加了一些方法

### test

对上述的几种数据结构进行测试