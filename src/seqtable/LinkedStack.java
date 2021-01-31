package seqtable;

// 链式栈
// 以单链表的头结点（不带哨兵）作为栈顶
public class LinkedStack<T> {
    // 链表的头指针
    // 也是栈的栈顶
    private Node top;
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    public void push(T element) {
        Node node = new Node(element);
        node.next = top;
        //System.out.println("before "+size);
        top = node;
        //System.out.println("after "+size);

        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T peek = top.data;
        top = top.next;
        size--;
        return peek;
    }

    public T peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    public LinkedStack<T> deepClone() {
        LinkedStack<T> reverse = new LinkedStack<>();
        Node p = top;
        for (int i = 0; i < size; i++) {
            reverse.push(p.data);
            p = p.next;
        }

        LinkedStack<T> duplicate = new LinkedStack<>();
        p = reverse.top;
        for (int i = 0; i < size; i++) {
            duplicate.push(p.data);
            p = p.next;
        }
        return duplicate;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("LinkedStack[");
        boolean first = true;
        Node p = top;
        while (p != null) {
            if (first) {
                first = false;
            } else {
                s.append(",");
            }
            s.append(p.data.toString());
            p = p.next;
        }
        s.append("]");
        return s.toString();
    }

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }
}