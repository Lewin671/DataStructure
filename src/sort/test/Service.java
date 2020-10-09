package sort.test;


import sort.Sort;

import java.util.Comparator;
import java.util.Random;

// 测试时间和验证正确性
public class Service<T> {
    private final Sort<T> sortMethod;
    private final Comparator<T> c;

    public Service(Sort<T> sortMethod) {
        this.sortMethod = sortMethod;
        this.c = sortMethod.getComparator();
    }

    static Integer[] generateIntegersRandomly(int n) {
        Integer[] res = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    public double sortTime() {
        long startTime = System.nanoTime();   //获取开始时间
        sortMethod.sort();  //测试的代码段
        long endTime = System.nanoTime(); //获取结束时间
        return (double) (endTime - startTime) / (1e6);
    }

    public boolean isCorrect() {
        sortMethod.sort();
        return verify(sortMethod.getData());
    }

    // 判断一个数组是否是升序
    public boolean verify(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (c.compare(a[i], a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}