package sort;

import java.util.Comparator;

public abstract class Sort<T> {
    protected T[] a;
    protected int low;
    protected int high;
    protected Comparator<T> c;

    private Sort(T[] a, int low, int high) {
        assert high >= low;
        this.a = a;
        this.low = low;
        this.high = high;
    }

    public Sort(T[] a, int low, int high, Comparator<T> c) {
        this(a, low, high);
        assert c != null;
        this.c = c;
    }

    public Sort(T[] a, Comparator<T> c) {
        this(a, 0, a.length, c);
    }

    public static <U> Sort<U> getSortInstance(SortType sortType, U[] data, Comparator<U> c) {
        Sort<U> sort;
        switch (sortType) {
            case InsertSort:
                sort = new InsertSort<>(data, c);
                break;
            case BubbleSort:
                sort = new BubbleSort<>(data, c);
                break;
            case SelectSort:
                sort = new SelectSort<>(data, c);
                break;
            case QuickSort:
                sort = new QuickSort<>(data, c);
                break;
            case ShellSort:
                sort = new ShellSort<>(data, c);
                break;
            case HeapSort:
                sort = new HeapSort<>(data, c);
                break;
            case DefaultSort:
                sort = new DefaultSort<>(data, c);
                break;
            case Mergesort:
                sort = new MergeSort<>(data, c);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortType);
        }
        return sort;
    }

    abstract public void sort();

    public T[] getData() {
        return a;
    }

    public Comparator<T> getComparator() {
        return c;
    }
}
