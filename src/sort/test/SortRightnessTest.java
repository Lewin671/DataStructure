package sort.test;

import sort.Sort;
import sort.SortType;

public class SortRightnessTest extends Test {
    // 对某种排序进行正确性测试
    static void Analysis(SortType sortType) throws Exception {
        for (int i = 0; i < testTimes; i++) {
            Integer[] data = Service.generateIntegersRandomly(n);
            Sort<Integer> sort = Sort.getSortInstance(sortType, data, c);
            if (!new Service<>(sort).isCorrect()) {
                throw new Exception(sortType + "错误");
            }
        }
        System.out.println(sortType + "没有发现错误");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("开始进行正确性测试");
        n = 1000;
        testTimes = 15;

        c = Integer::compareTo;

        Analysis(SortType.InsertSort);
        Analysis(SortType.QuickSort);
        Analysis(SortType.DefaultSort);
        Analysis(SortType.ShellSort);
        Analysis(SortType.BubbleSort);
        Analysis(SortType.HeapSort);
        Analysis(SortType.Mergesort);
    }
}
