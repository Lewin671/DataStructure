package sort;

import java.util.Comparator;

public class HeapSort<T> extends Sort<T> {
    private int shift; // [low,high) -> [1,high-low+1)
    private int n; // 堆的大小

    public HeapSort(T[] a, int low, int high, Comparator<T> c) {
        super(a, low, high, c);
        shift = low - 1;
        n = high - low;
    }

    public HeapSort(T[] a, Comparator<T> c) {
        super(a, c);
        shift = low - 1;
        n = high - low;
    }

    @Override
    public void sort() {
        buildHeap();

        //System.out.println("建堆成功");
        //System.out.println("建堆后的数组: "+ Arrays.asList(a));

        for (int i = 1; i < high - low; i++) {
            T tmp = a[1 + shift];
            a[1 + shift] = a[n + shift];
            a[n + shift] = tmp;

            n--;
            reBuild(1 + shift);
        }
    }

    // 建立堆，虽然上界是O(nlog(n))，但是它有更加精确的上界O(n)
    private void buildHeap() {
        for (int i = n / 2; i > 0; i--) {
            reBuild(i + shift);
        }
    }

    // 保持以root为根结点的最大堆，并且root的左右子树都已经是最大堆了
    private void reBuild(int root) {
        // 因为最后一个叶子结点是[n/2]+1，所以最后一个分支结点是[n/2]
        // 一个叶子结点自然满足最大堆
        int cur = root - shift;

        while (cur <= n / 2) {
            int maxChildIndex = cur * 2;
            if (maxChildIndex + 1 <= n
                    && c.compare(a[maxChildIndex + shift], a[maxChildIndex + 1 + shift]) < 0) {
                maxChildIndex++;
            }

            if (c.compare(a[cur + shift], a[maxChildIndex + shift]) < 0) {
                T tmp = a[cur + shift];
                a[cur + shift] = a[maxChildIndex + shift];
                a[maxChildIndex + shift] = tmp;

                cur = maxChildIndex;
            } else {
                break;
            }
        }
    }
}
