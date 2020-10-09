package sort;

import java.util.Comparator;

// 冒泡排序
// 这里采用的是上浮冒泡排序
// 如果采用下沉-上浮-下沉-上浮的交替方式的冒泡排序速度会快一些
public class BubbleSort<T> extends Sort<T> {

    public BubbleSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
    }

    public BubbleSort(T[] a, Comparator<T> c) {
        super(a, c);
    }

    // 这里有一个小小的改进
    // 假设需要排序的序列为: a1 a2 ... aj aj+1 ... an
    // 如果某次上浮过程中，最后一次交换发生在aj和aj+1上，说明这一趟排序之后，
    // [j+1,n)的元素都是排好序的
    // 那么下次就只需要对[0,j+1)的范围进行排序即可
    @Override
    public void sort() {
        // 初始化
        int n = high - low;
        int bound = n; // 某次排序后，需要冒泡元素的下标的上界
        while (bound > 0) {
            int t = 0;
            for (int i = 0; i < bound - 1; i++) {
                if (c.compare(a[i], a[i + 1]) > 0) {
                    T tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    t = i + 1;
                }
            }
            bound = t;
        }
    }
}
