package ch22_归并排序;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/* 自顶向下的归并排序
 * 改进1:当为小数组时,采用插入排序
 * 改进2:检测数组是否有序
 * 改进3:在递归中交换参数避免数组复制
 */

public class MergeOptimization {
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone(); // 使用局部变量而不是使用静态成员变量作为辅助数组
        aux = new Comparable[a.length];
        sort(aux, a, 0, a.length - 1);
    }

    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // 小数组使用插入排序
        if (lo + 20 >= hi) {
            for (int i = lo; i <= hi; ++i) {
                for (int j = i; j > lo && less(dst[j], dst[j - 1]); --j)
                    swap(dst, j, j - 1);
            }
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(dst, src, lo, mid); // 将左半部排序
        sort(dst, src, mid + 1, hi); // 将右半部排序

        // 如果已经有序，则不需要归并元素,把辅助数组src拷贝到dst即可
        if (!less(src[mid + 1], src[mid])) {
            // 把src[lo...hi]拷贝到dst[lo...hi]
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi); // 归并结果
    }

    // 将已经排序好的数组a[lo]...a[mid]和a[mid+1]...a[hi]归并
    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        // 归并到a[lo]...a[hi]
        for (int k = lo; k <= hi; ++k) {
            if (i > mid)
                dst[k] = src[j++];
            else if (j > hi)
                dst[k] = src[i++];
            else if (less(src[i], src[j]))
                dst[k] = src[i++];
            else
                dst[k] = src[j++];
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

        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
