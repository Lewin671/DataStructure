package sort;

import search.BinarySearch;

import java.util.Comparator;

public class MergeSort<T> extends Sort<T> {

    private T[] tmp;

    public MergeSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
        tmp = (T[]) new Object[high - low];
    }

    public MergeSort(T[] a, Comparator<T> c) {
        super(a, c);
        tmp = (T[]) new Object[high - low];
    }

    @Override
    public void sort() {
        mergeSort(a, low, high, c);
    }


    private void mergeSort(T[] a, int low, int high, Comparator<T> c) {
        int mid = (low + high) >> 1;
        if (mid - low > 1) {
            mergeSort(a, low, mid, c);
        }
        if (high - mid > 1) {
            mergeSort(a, mid, high, c);
        }
        merge(a, low, mid, high, c);
    }

    public void merge(T[] a, int low, int mid, int high, Comparator<T> c) {
        if (high - low < 2) return;

        for (int i = low; i < high; i++) {
            tmp[i - low] = a[i];
        }

        int i = 0;
        int j = mid - low;

        /*
        // 这种写法选择分支比较多，速度会稍微慢一点
        for(int k=low;k<high;k++){
            if((i>=mid-low)||(j<high-low && c.compare(tmp[i],tmp[j])>0)){
                a[k] = tmp[j++];
            }else {
                a[k] = tmp[i++];
            }
        }
         */

        int k = low;
        while (k < high && i < mid - low && j < high - low) {
            if (c.compare(tmp[i], tmp[j]) > 0) {
                a[k] = tmp[j++];
            } else {
                a[k] = tmp[i++];
            }
            k++;
        }

        while (i < mid - low) {
            a[k++] = tmp[i++];
        }

        while (j < high - low) {
            a[k++] = tmp[j++];
        }

    }

    public void merge1(T[] a, int low, int mid, int high, Comparator<T> c) {

        if (high - low > 20000) {
            // for all i in [low,low1), a[i] < a[mid-low]
            int low1 = BinarySearch.upper_bound(a, low, mid, c, a[mid]);
            // for all i in [high1,high), a[i] >= a[mid-low]
            int high1 = BinarySearch.lower_bound(a, mid, high, c, a[mid - 1]);

            int intervalLen = high - low;
            int reducedLen = intervalLen - (high1 - low1);
            System.out.println("减少的长度:" + reducedLen + "/" + intervalLen + "=" + (double) reducedLen / intervalLen * 100 + "%");


            merge(a, low1, mid, high1, c);
        } else {
            merge(a, low, mid, high, c);
        }
    }
}