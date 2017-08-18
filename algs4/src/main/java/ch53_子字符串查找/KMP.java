package ch53_子字符串查找;

import java.util.*;

/**
 * Created by WQS on 2017/8/16.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class KMP {

    private String pattern;
    private int[][] dfa;

    public KMP(String pattern) {
        this.pattern = pattern;
        int M = pattern.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1;
        for (int X = 0, i = 1; i < M; ++i) {
            for (int c = 0; c < R; ++c)
                dfa[c][i] = dfa[c][X];
            dfa[pattern.charAt(i)][i] = i + 1;
            // 下一个重启状态就是前一个重启状态在当前输入字符后的迁移状态
            X = dfa[pattern.charAt(i)][X];
        }
    }

    public int search(String s) {
        int i, j;
        for (i = 0, j = 0; i < s.length() && j < pattern.length(); ++i)
            j = dfa[s.charAt(i)][j];
        if (j == pattern.length())
            return i - pattern.length();
        else
            return -1;  //没有找到匹配
    }

    public static void main(String[] args) {
        String pattern = "AACAA";
        KMP kmp = new KMP(pattern);

        String s = "AABRAACADABRAACAADABRA";
        int index = kmp.search(s);
        System.out.println(index);
        for (int k = index, i = 0; i < pattern.length(); ++i)
            System.out.print(s.charAt(k++));
        System.out.println();

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());
        System.out.println(pq.isEmpty());
    }

}
