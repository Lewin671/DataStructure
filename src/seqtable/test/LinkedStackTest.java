package seqtable.test;

import seqtable.LinkedStack;

public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(22);
        stack.push(44);
        stack.push(33);
        stack.push(55);

        System.out.println(stack.size());

        stack.pop();
        System.out.println(stack.size());

        System.out.println(stack.peek());

        stack.clear();
        System.out.println(stack);

        System.out.println(stack.peek());
    }
}
