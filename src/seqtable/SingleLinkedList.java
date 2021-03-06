package seqtable;

import java.util.Iterator;

/**
 * 带有哨兵结点的单链表容器
 *
 * @param <T> 容器内部元素的类型参数
 */
public class SingleLinkedList<T> {
    private final Node head;
    private int size;

    /**
     * 默认构造函数，创建一个空的单向链表，只有一个哨兵元素
     */
    public SingleLinkedList() {
        head = new Node();
        size = 0;
    }

    /**
     * @return 链表中存储元素的个数（不包含哨兵）
     */
    public int size() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return true 当且仅当链表只有哨兵元素
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element 检查的元素
     * @return 如果包含element，则返回true，否则返回false
     */
    public boolean contains(T element) {
        Iterator<T> it = iterator();

        while (it.hasNext()) {
            if (it.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 链表的迭代器
     */
    public Iterator<T> iterator() {
        // 返回匿名内部类
        return new Iterator<T>() {
            private Node p = head;

            @Override
            public boolean hasNext() {
                return p.next != null;
            }

            @Override
            public T next() {
                p = p.next;
                return p.data;
            }
        };
    }

    /**
     * 添加元素到链表尾部
     *
     * @param element 要添加的元素
     */
    public void add(T element) {
        getTailItem().next = new Node(element);
        size++;
    }

    /**
     * @param element 要删除的元素，根据判断两个元素是否equal来删除。 一次调用最多删除一次
     */
    public void remove(T element) {
        Node p = head;
        while (p.next != null) {
            if (p.next.data.equals(element)) {
                Node removedNode = p.next;
                p.next = removedNode.next;
                removedNode.next = null;
                size--;
                return;
            }
            p = p.next;
        }

        // element元素不在链表中
    }

    /**
     * 清空链表
     */
    public void clear() {
        while (!isEmpty()) {
            remove(head.next.data);
        }
    }

    /**
     * @param index 第index个元素的下标,index从o开始
     * @return 获取第index元素的值
     */
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * @param index   第index个元素
     * @param element 要设置的元素值
     * @return 返回设置前的元素值
     */
    public T set(int index, T element) {
        Node node = getNode(index);
        T ret = node.data;
        node.data = element;
        return ret;
    }

    /**
     * @param index 要函数元素的下标
     * @return 被删除的元素的值
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = getNode(index);
        remove(node.data);
        return node.data;
    }

    /**
     * @return 单链表的字符串
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("SingleLinkedList[");
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            s.append(iterator.next().toString());
            if (iterator.hasNext()) {
                s.append(",");
            }
        }
        s.append("]");

        s.append(" size: ");
        s.append(size);
        return s.toString();
    }

    /**
     * @param index 元素的下标，从0开始计算
     * @return 第i个链表结点
     */
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * @return 返回链表尾部结点
     */
    private Node getTailItem() {
        if (isEmpty()) return head;
        return getNode(size - 1);
    }

    // 内部节点
    private class Node {
        T data;
        Node next;

        Node() {
            next = null;
        }

        Node(T data) {
            this.data = data;
            next = null;
        }
    }
}
