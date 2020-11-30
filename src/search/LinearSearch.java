package search;

import java.util.Comparator;

public class LinearSearch {
    // 最原始的线性查找
    public static <T> int originalLinearSearch(T[] a, int low, int high, Comparator<T> c, T key){
        for (int i = low; i < high; i++) {
            if(c.compare(a[i],key) == 0){
                return i;
            }
        }
        return -1;
    }

    // optimised method 1 of linear search
    // untested
    public static <T> int optimised1LinearSearch(T[] a, int low, int high, Comparator<T> c, T key){
        assert a.length > high;
        a[high] = key;
        int i = low;
        // 这个算法主要是优化在减少了条件的判断个数
        while (c.compare(a[i++],key) != 0);
        return i==high?-1:i-1;
    }

    // optimised method 2 of linear search
    // untested
    public static <T> int optimised2LinearSearch(T[] a, int low, int high, Comparator<T> c, T key){
        assert a.length > high;
        a[high] = key;
        int i = low;
        // 这个优化算法相比第一个优化算法，主要是优化在i的推进次数少了接近50%
        while (true){
            if(c.compare(a[i],key) == 0){
                break;
            }

            if(c.compare(a[i+1],key) == 0){
                i = i + 1;
                break;
            }
            i = i + 2;
        }
        return i==high?-1:i-1;
    }


}
