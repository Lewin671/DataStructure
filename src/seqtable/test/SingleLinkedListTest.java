package seqtable.test;

import seqtable.SingleLinkedList;

public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        // add
        System.out.println("添加元素0-9");
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        // remove
        System.out.println("删除元素9");
        list.remove(new Integer(9));
        System.out.println(list);

        // clear
        System.out.println("clear");
        list.clear();
        System.out.println(list);

        // add
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        // get
        System.out.println("get element by index = 3");
        System.out.println(list.get(3));

        // set
        System.out.println("set element = 33 with index = 3 ");
        System.out.println(list.set(3, 33));
        System.out.println(list);

        System.out.println("remove by index: " + list.remove(11));
        System.out.println(list);

        System.out.println("contains 33? " + list.contains(33));
        System.out.println("contains 3? " + list.contains(3));
    }
}
