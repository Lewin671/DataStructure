package sort.test;

import java.util.Comparator;
import java.util.List;

public class Test {
    static int testTimes;
    static int n;
    static Comparator<Integer> c;

    static double mean(List<Double> list) {
        if (list.size() > 2) {
            removeBiggestAndSmallestData(list);
        }
        double res = 0;
        int n = list.size();
        for (double item : list) {
            res += item;
        }
        return res / n;
    }

    static void removeBiggestAndSmallestData(List<Double> list) {
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(maxIndex) < list.get(i)) {
                maxIndex = i;
            }
        }

        list.remove(maxIndex);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(minIndex) > list.get(i)) {
                minIndex = i;
            }
        }
        list.remove(minIndex);
    }
}
