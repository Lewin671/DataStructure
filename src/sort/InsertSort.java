package sort;

import search.BinarySearch;

import java.util.Comparator;

public class InsertSort<T> extends Sort<T> {
    public InsertSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public InsertSort(T[] a, Comparator<T> c) {
        super(a, c);
    }


    // 在查找过程中使用二分查找
    public void sort(){
        int n = high - low;

        for (int i = 1; i < n; i++) {
            T key = a[low + i];
            int pos = BinarySearch.upper_bound(a, low, low + i, c, key);

            int j = low + i;
            while (j > pos) {
                a[j] = a[j - 1];
                j--;
            }

            a[pos] = key;
        }


    }

    // 直接插入排序
    public void legencyInsertSort() {
        int n = high - low;
        for (int i = 1; i < n; i++) {
            T key = a[low + i];

            int j = low + i;
            while (j > low && c.compare(a[j - 1], key) > 0) {
                a[j] = a[j - 1];
                j--;
            }

            a[j] = key;
        }
    }
}
