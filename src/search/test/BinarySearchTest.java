package search.test;

import search.BinarySearch;

public class BinarySearchTest {
    public static void main(String[] args) throws Exception {
        // 给定一个有序数组
        Integer[] data = {-1, 2, 3, 4, 5, 5, 6, 7, 8};
        int key = 0;
        System.out.println(BinarySearch.lower_bound(data, 0, data.length, Integer::compareTo, key));
        System.out.println(BinarySearch.upper_bound(data, 0, data.length, Integer::compareTo, key));
        System.out.println(BinarySearch.binary_search(data, 0, data.length, Integer::compareTo, key));
    }
}
