package ch22_归并排序;

import ch22_归并排序.MergeOptimization;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("MergeOptimization"))
            MergeOptimization.sort(a);
        if (alg.equals("Merge"))
            Merge.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // 使用算法alg将T个长度为N的数组排序
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; ++t) {
            // 进行一次测试，累计排序用的时间
            for (int i = 0; i < N; ++i)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);    //待排序数组的元素个数
        int T = Integer.parseInt(args[3]);    //运行排序的次数
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.println(alg1 + ":" + t1);
        StdOut.println(alg2 + ":" + t2);
        StdOut.println(alg1 + "/" + alg2 + ":" + t1 / t2);
    }

}
