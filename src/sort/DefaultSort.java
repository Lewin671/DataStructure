package sort;

import java.util.Arrays;
import java.util.Comparator;

public class DefaultSort<T> extends Sort<T> {
    public DefaultSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public DefaultSort(T[] a, Comparator<T> c) {
        super(a, c);
    }

    public void sort() {
        Arrays.sort(a, low, high, c);
    }
}

