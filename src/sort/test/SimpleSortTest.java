package sort.test;

import sort.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SimpleSortTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 输入n
        // 然后输入n个整数
        int n = in.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        // 定义比较方式
        Comparator<Integer> comparator = Integer::compareTo;

        // 开始测试不同的排序

        Integer[] b = Arrays.copyOf(a, n);
        System.out.println("排序前的结果: " + Arrays.asList(b));

        Sort<Integer> sortObj = new InsertSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("直接插入排序后的结果: " + Arrays.asList(b));

        b = Arrays.copyOf(a, n);
        sortObj = new ShellSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("Shell排序后的结果: " + Arrays.asList(b));

        b = Arrays.copyOf(a, n);
        sortObj = new BubbleSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("冒泡排序后的结果: " + Arrays.asList(b));

        b = Arrays.copyOf(a, n);
        sortObj = new QuickSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("分划排序后的结果: " + Arrays.asList(b));

        b = Arrays.copyOf(a, n);
        sortObj = new HeapSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("堆排序后的结果: " + Arrays.asList(b));

        b = Arrays.copyOf(a, n);
        sortObj = new MergeSort<>(b, 0, n, comparator);
        sortObj.sort();
        System.out.println("合并排序后的结果: " + Arrays.asList(b));

        in.close();
    }
}
