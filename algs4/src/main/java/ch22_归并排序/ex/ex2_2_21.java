package ch22_归并排序.ex;

//import edu.princeton.cs.algs4.Merge;

import ch22_归并排序.Merge;

//线性对数时间找出三份列表的公共元素,如果有,返回第一个被找到的公共元素
public class ex2_2_21 {

    public static Comparable findCommonElement(Comparable[] A, Comparable[] B, Comparable[] C) {
        Merge.sort(A);
        Merge.sort(B);
        Merge.sort(C);

        int goA = 0, goB = 0, goC = 0;
        int len = A.length;
        while (goA < len && goB < len && goC < len) {
            if (equal(C[goC], A[goA]) && equal(C[goC], B[goB]))
                return A[goA];
            Comparable max = max(A[goA], B[goB], C[goC]);
            if (less(A[goA], max))
                ++goA;
            if (less(B[goB], max))
                ++goB;
            if (less(C[goC], max))
                ++goC;
        }
        return null;
    }

    private static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    private static Comparable max(Comparable a, Comparable b, Comparable c) {
        Comparable t = a;
        if (a.compareTo(b) < 0)
            t = b;
        if (t.compareTo(c) < 0)
            t = c;
        return t;
    }

    public static void main(String[] args) {
        Integer[] A = {1, 6, 16, -9, 0};
        Integer[] B = {4, 6, 16, -9, 0};
        Integer[] C = {6, 6, -7, -9, 0};
        System.out.println(findCommonElement(A, B, C));
    }
}
