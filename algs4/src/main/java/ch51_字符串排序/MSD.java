package ch51_字符串排序;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WQS on 2017/8/9.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class MSD {
    private static final int R = 256;
    private static final int THRESHOLD = 15;
    private static String[] aux;

    private static int charAt(String s, int index) {
        if (index >= s.length())
            return -1;
        return s.charAt(index);
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    /**
     * 以字符串第d个字符为键排序字符串a[lo...hi]
     *
     * @param a
     * @param lo
     * @param hi
     * @param d
     */
    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + THRESHOLD) {
            insertSort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R + 2];
        // 计算频率
        for (int i = lo; i <= hi; ++i)
            count[charAt(a[i], d) + 2]++;

        for (int i = 0; i < R + 1; ++i)
            count[i + 1] += count[i];

        for (int i = lo; i <= hi; ++i)
            aux[count[charAt(a[i], d) + 1]++] = a[i];

        for (int i = 0; i < R; ++i)
            sort(a, lo + count[i], lo + count[i + 1] - 1, d + 1);
    }

    private static void insertSort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; ++i)
            for (int j = i; j > lo && less(a[j], a[j - 1], d); --j)
                exch(a, j, j - 1);
    }

    private static void exch(String[] a, int i, int j) {
        String s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    private static boolean less(String s1, String s2, int d) {
        return s1.substring(d).compareTo(s2.substring(d)) < 0;
    }

    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch51_字符串排序/lsd.txt"));
        List<String> t = new ArrayList<>();
        while (!in.isEmpty())
            t.add(in.readString());
        String[] a = t.toArray(new String[]{});
        sort(a);
        Arrays.asList(a).forEach(StdOut::println);
    }
}
