package ch23_快速排序;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PerfectQuickArray {
    public static void sort(Comparable[] a) {
        // StdRandom.shuffle( a );
        ConstructPerfectQuickArray(a, a.length / 2, 0, a.length - 1);
        show(a);
        sort(a, 0, a.length - 1);
    }

    public static void ConstructPerfectQuickArray(Comparable[] a, int keyIndex, int lo, int hi) {
        if (hi - lo < 2)
            return;

        int lo1 = lo;
        int hi1 = keyIndex - 1;
        int keyIndex1 = (hi1 - lo1) / 2 + lo1;

        int lo2 = keyIndex + 1;
        int hi2 = hi;
        int keyIndex2 = (hi2 - lo2) / 2 + lo2;

        ConstructPerfectQuickArray(a, keyIndex1, lo1, hi1);
        ConstructPerfectQuickArray(a, keyIndex2, lo2, hi2);
        swap(a, lo, keyIndex);
    }

    // 快速排序,升序
    // 时间：O(NlogN),空间：logN
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        System.out.println("[" + (lo + 1) + "--" + j + "]" + "[" + (j + 1) + "]" + "[" + (j + 2) + "--" + hi + "]");
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // 此分区方法对于在几种重复元素时,性能很差,因此建议采用三向切分
    public static int partition(Comparable[] a, int lo, int hi) {
        // 以key = a[lo]为参考,a的左边都小于key,右边都大于等于key
        int keyIndex = lo;
        Comparable key = a[keyIndex];
        // System.out.println("key:" + key + " lo:" + lo);
        for (int i = lo + 1; i <= hi; ++i) {
            if (less(a[i], key))
                swap(a, ++keyIndex, i);
        }
        // 把key放在正确的位置上
        swap(a, keyIndex, lo);
        return keyIndex;
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
        // int N = 20;
        // Integer[] a = new Integer[N];
        // for( int i = 0; i < N; ++i )
        // a[i] = StdRandom.uniform(100);
        //Integer[] a = { 9, 1, 3, 2, 6, 5, 7, 8, 4, 13, 10, 12, 11, 15, 14, 16, 17 };
        Integer[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
