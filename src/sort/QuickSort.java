package sort;

import java.util.Comparator;

public class QuickSort<T> extends Sort<T> {
    public QuickSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public QuickSort(T[] a, Comparator<T> c) {
        super(a, c);
    }

    @Override
    public void sort() {
        quickSort(low, high);
    }

    // 分划操作
    // 这里的pivot基准选择的是区间[low,high)内的第一个元素a[low]
    private int partition(int low, int high) {
        int i = low;
        int j = high;
        T pivot = a[low];
        while (i < j) {
            i = i + 1;
            // 注意: 这里是可能会越界的
            while (c.compare(a[i], pivot) < 0) {
                i++;
            }

            j = j - 1;
            while (c.compare(a[j], pivot) > 0) {
                j--;
            }

            if (i < j) {
                T tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }

        a[low] = a[j]; // 此时j = i或者j = i-1
        a[j] = pivot;
        return j;
    }

    private void quickSort(int low, int high) {
        //元素个数小于2，则不需要排序
        if (high - low < 2) return;

        // 为了让快速排序的分划尽可能地均匀，这里选择最左、
        // 最右和中间元素的中位数作为基准pivot
        int mid = (low + high) / 2;

        // a[low] <= a[high]
        if (c.compare(a[low], a[high - 1]) > 0) {
            T tmp = a[low];
            a[low] = a[high - 1];
            a[high - 1] = tmp;
        }

        if (high - low == 2) return;


        // a[mid] <= a[high]
        if (c.compare(a[mid], a[high - 1]) > 0) {
            T tmp = a[mid];
            a[mid] = a[high - 1];
            a[high - 1] = tmp;
        }

        // a[mid] <= a[low] <= a[high]
        if (c.compare(a[mid], a[low]) > 0) {
            T tmp = a[mid];
            a[mid] = a[low];
            a[low] = tmp;
        }

        int j = partition(low, high);
        quickSort(low, j);
        quickSort(j + 1, high);
    }

}
