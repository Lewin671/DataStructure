package sort;

import java.util.Comparator;


// 注意，选择排序是不稳定排序，因为在交换过程中会影响稳定性
public class SelectSort<T> extends Sort<T> {
    public SelectSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public SelectSort(T[] a, Comparator<T> c) {
        super(a, c);
    }

    public void sort() {
        int n = high - low;
        for (int i = 1; i < n; i++) {
            int positionOfMax = low;
            for (int j = low; j <= high - i; j++) {
                if (c.compare(a[positionOfMax], a[j]) < 0) {
                    positionOfMax = j;
                }
            }

            T tmp = a[positionOfMax];
            a[positionOfMax] = a[high - i];
            a[high - i] = tmp;
        }
    }
}