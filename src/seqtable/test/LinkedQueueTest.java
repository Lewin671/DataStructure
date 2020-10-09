package seqtable.test;

import seqtable.LinkedQueue;

public class LinkedQueueTest {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.push(22);
        queue.push(44);
        queue.push(33);
        System.out.println(queue.size());
        System.out.println(queue);

        queue.pop();
        System.out.println(queue.peek());

        queue.clear();
        System.out.println(queue.size());

        System.out.println(queue.peek());
    }
}
