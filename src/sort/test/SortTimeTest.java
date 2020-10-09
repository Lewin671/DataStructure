package sort.test;

import sort.Sort;
import sort.SortType;

import java.util.ArrayList;

// 对排序时间的测试
public class SortTimeTest extends Test {

    // 对某种排序进行时间性能测试
    // 虽然对每种排序测试的数据不一样，但是其分布是一样的
    // 当测试样本量大的时候，根据大数定律，其运行时间的均值都收敛于该算法的期望运行时间
    static void Analysis(SortType sortType) {
        ArrayList<Double> runTime = new ArrayList<>();

        for (int i = 0; i < testTimes; i++) {
            Integer[] data = Service.generateIntegersRandomly(n);
            Sort<Integer> sort = Sort.getSortInstance(sortType, data, c);
            runTime.add(new Service<>(sort).sortTime());
        }

        System.out.println(sortType + "平均运行时间: " + mean(runTime));
    }


    public static void main(String[] args)  {
        System.out.println("开始运行时间测试");
        n = 500000;
        testTimes = 10;

        c = Integer::compareTo;

        //Analysis(SortType.InsertSort);
        Analysis(SortType.QuickSort);
        Analysis(SortType.DefaultSort);
        //Analysis(SortType.ShellSort);
        //Analysis(SortType.BubbleSort);
        Analysis(SortType.HeapSort);
        Analysis(SortType.Mergesort);
    }
}



