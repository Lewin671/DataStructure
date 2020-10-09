package seqtable;

public class LinkedQueue<T> {
    private Node front, rear;
    private int size;
    public LinkedQueue() {
        size = 0;
        front = rear = null;
    }

    public boolean push(T element) {
        Node node = new Node(element);
        if (node == null) return false;

        // 插入
        if (isEmpty()) {
            front = node;
        } else {
            rear.next = node;
        }

        rear = node;
        size++;
        return true;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        T element = front.data;
        front = front.next;
        size--;

        if (front == null) {
            rear = null;
        }
        return element;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    public T peek() {
        if (isEmpty()) return null;
        return front.data;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("LinkedQueue[");
        Node p = front;
        while (p != null) {
            if (p != front) {
                s.append(",");
            }
            s.append(p.data);
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
