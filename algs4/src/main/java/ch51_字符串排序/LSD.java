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
public class LSD {

    /**
     * 对字符串数组a排序,假设字符串长度都为width
     *
     * @param a
     * @param width
     */
    public static void sort(String[] a, int width) {
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];

        for (int d = width - 1; d >= 0; --d) {

            int[] count = new int[R + 1];

            for (int i = 0; i < N; ++i)
                count[a[i].charAt(d) + 1]++;

            for (int i = 0; i < R; ++i)
                count[i + 1] += count[i];

            for (int i = 0; i < N; ++i)
                aux[count[a[i].charAt(d)]++] = a[i];

            for (int i = 0; i < N; ++i)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch51_字符串排序/lsd.txt"));
        List<String> t = new ArrayList<>();
        while (!in.isEmpty())
            t.add(in.readString());
        String[] a = t.toArray(new String[]{});

        sort(a, a[0].length());

        Arrays.asList(a).forEach(StdOut::println);
    }
}
