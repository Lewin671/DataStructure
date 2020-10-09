package search;

import java.util.Comparator;

public class BinarySearch {
    /**
     * @return the first index corresponding element > key
     */
    public static <T> int upper_bound(T[] a, int low, int high, Comparator<T> c, T key) {
        if (high - low <= 0) {
            try {
                throw new Exception("The searching interval is not valid!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (low < high) {
            int mid = (low + high) >> 1;
            if (c.compare(a[mid], key) > 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    /**
     * @return the first index corresponding element >= key
     */
    public static <T> int lower_bound(T[] a, int low, int high, Comparator<T> c, T key) {
        if (high - low <= 0) {
            try {
                throw new Exception("The searching interval is not valid!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (low < high) {
            int mid = (low + high) >> 1; // low <= mid < high
            if (c.compare(a[mid], key) >= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * @param a    给定的升序数组
     * @param low  查找范围的起始下标
     * @param high 查找范围的末尾下标(不包括high)
     * @param c    比较器
     * @param key  要查找的值
     * @param <T>  查找元素的类型
     * @return the index of an element equal to the given key.
     * If there are multiple values equal to the key, return any of them. There are no extra limitation.
     * If there doesn't exist an element equal to the key, return -1.
     */
    public static <T> int binary_search(T[] a, int low, int high, Comparator<T> c, T key) {
        if (high - low <= 0) {
            try {
                throw new Exception("The searching interval is not valid!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int mid;
        while (low < high) {
            mid = (low + high) >> 1;
            int cmp = c.compare(a[mid], key);
            // a[mid] > key，说明key值只可能落在[low,mid)之间
            if (cmp > 0) {
                high = mid;
            } else if (cmp < 0) { // a[mid] < key，说明key值只可能落在[mid+1,high)上
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

