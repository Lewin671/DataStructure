package seqtable.test;

import seqtable.SingleLinkedList;

public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        // add
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println("size: " + list.size());

        // remove
        list.remove(new Integer(9));
        System.out.println(list);
        System.out.println("size: " + list.size());

        // clear
        list.clear();
        System.out.println(list);
        System.out.println("size: " + list.size());

        // add
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println("size: " + list.size());

        // get,set
        System.out.println(list.get(3));
        System.out.println(list.set(3, 33));
        System.out.println(list);
        System.out.println("size: " + list.size());

        list.remove(11);
        System.out.println(list);
        System.out.println("size: " + list.size());
    }
}
