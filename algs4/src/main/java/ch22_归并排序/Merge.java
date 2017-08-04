package ch22_归并排序;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by WQS on 2017/8/4.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class Merge {

    private static Comparable[] aux = null;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid); // 将左半部排序
            sort(a, mid + 1, hi); // 将右半部排序
            merge(a, lo, mid, hi); // 归并结果
        }
    }

    // 将已经排序好的数组a[lo...mid]和a[mid+1...hi]归并
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        // 把a[lo]...a[hi]复制到辅助数组aux
        for (int k = lo; k <= hi; ++k)
            aux[k] = a[k];

        // 归并到a[lo]...a[hi]
        for (int k = lo; k <= hi; ++k) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }

    // v < w 返回true,否则返回false
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // 交换a[i] a[j]
    private static void swap(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 打印数组a
    private static void show(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; ++i)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    // 判断数组a是否为升序
    public static boolean isSorted(Comparable[] a) {
        int len = a.length;
        for (int i = 1; i < len; ++i)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; ++i)
            a[i] = StdRandom.uniform(100);
        show(a); // 打印排序前数组
        sort(a);
        assert isSorted(a);
        show(a); // 打印排序后数组
    }
}
