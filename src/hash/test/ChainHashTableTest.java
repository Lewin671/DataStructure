package hash.test;

import hash.ChainHashTable;

public class ChainHashTableTest {
    public static void main(String[] args) {
        ChainHashTable<String,Integer> hashTable = new ChainHashTable<>();
        hashTable.insert("lqy",11);

        System.out.println(hashTable.get("lqy"));
        System.out.println(hashTable.size());

        hashTable.set("lqy",12);
        System.out.println(hashTable.get("lqy"));
        System.out.println(hashTable.size());
    }
}
