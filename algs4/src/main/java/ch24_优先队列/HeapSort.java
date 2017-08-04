package ch24_优先队列;

import edu.princeton.cs.algs4.StdRandom;

public class HeapSort {
    // 堆排序,升序
    // 时间：O(NlogN),空间：1
    public static void sort(Comparable[] a) {
        // a[0]不参与堆排序,只对a[1...a.length-1]排序
        int len = a.length - 1; // len个元素构造堆

        // 构造堆
        for (int k = len / 2; k >= 1; --k)
            sink(a, k, len);

        // 下沉排序
        while (len > 1) {
            swap(a, 1, len);
            --len;
            sink(a, 1, len);
        }
    }

    // 下沉a[1....len]的a[k]
    public static void sink(Comparable[] a, int k, int len) {
        while (2 * k <= len) {
            int j = 2 * k;

            // 找出子节点中较大的元素的索引
            if (j < len && less(a, j, j + 1))
                ++j;

            // a[k] >= a[2k]或a[2k+1],表明此节点大于等于它的子节点,满足堆有序
            if (!less(a, k, j))
                break;

            // 把a[k]和其子节点的较大者交换位置
            swap(a, k, j);

            k = j;
        }
    }

    // 上浮a[1....len]的a[k]
    public static void swim(Comparable[] a, int k, int len) {
        while (k > 1 && less(a, k / 2, k)) {
            swap(a, k, k / 2);
            k = k / 2;
        }
    }

    public static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    // 判断数组a是否为升序,不包括a[0]
    public static boolean isSorted(Comparable[] a) {
        int len = a.length - 1;
        for (int i = 2; i <= len; ++i)
            if (less(a, i, i - 1))
                return false;
        return true;
    }

    // 打印数组a,不包括a[0]
    private static void show(Comparable[] a) {
        int len = a.length - 1;
        for (int i = 1; i <= len; ++i)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 13;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; ++i) {
            a[i] = StdRandom.uniform(-100, 100);
            System.out.print(a[i] + " ");
        }
        System.out.println();

        // 堆排序,只对a[1...a.length-1]排序
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }

}
