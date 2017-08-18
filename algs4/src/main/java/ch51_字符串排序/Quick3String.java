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
public class Quick3String {
    private static int charAt(String s, int index) {
        if (index >= s.length())
            return -1;
        return s.charAt(index);
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {

        if (hi <= lo)
            return;

        int lt = lo, gt = hi;
        int i = lo + 1;
        int v = charAt(a[lo], d);
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)
                exch(a, lt++, i++);
            else if (t > v)
                exch(a, i, gt--);
            else
                ++i;
        }

        sort(a, lo, lt - 1, d);
        if (v >= 0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
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

    private static void exch(String[] a, int i, int j) {
        String s = a[i];
        a[i] = a[j];
        a[j] = s;
    }


}
