package sort;

import java.util.Comparator;

// 希尔排序
public class ShellSort<T> extends Sort<T> {

    /*
    public ShellSort(T[] a, int low, int high) {
        super(a, low, high);
    }
    */

    public ShellSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public ShellSort(T[] a, Comparator<T> c) {
        super(a, c);
    }

    @Override
    public void sort() {
        int n = high - low; // 排序元素的个数

        int d = n / 2; // 初始增量

        while (d > 0) {
            // 前d个元素是d组的第一个元素，故不需插入
            for (int i = d; i < n; i++) {
                T key = a[i]; // 待插入的元素
                int j = i - d;

                while (j >= 0 && c.compare(a[j], key) > 0) {
                    a[j + d] = a[j];
                    j = j - d;
                }

                // 此时 j < 0 或者 a[j] <= key
                a[j + d] = key;
            }
            d = d / 2;
        }

    }
}
