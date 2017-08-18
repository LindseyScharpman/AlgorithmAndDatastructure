package ch53_子字符串查找;

/**
 * Created by WQS on 2017/8/16.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

/**
 * 暴力搜索子字符串
 */
public class BF {

    public static int search(String txt, String pattern) {
        int i, j;
        int M = pattern.length(), N = txt.length();
        for (i = 0, j = 0; i < N && j < M; ++i) {
            if (txt.charAt(i) == pattern.charAt(j))
                ++j;
            else {
                i -= j;
                j = 0;
            }
        }

        if (j == M)
            return i - M;
        else
            return -1;
    }

    public static void main(String[] args) {
        String pattern = "AACAA";

        String s = "AABRAACADABRAACAADABRA";
        int index = search(s, pattern);
        System.out.println(index);
        for (int k = index, i = 0; i < pattern.length(); ++i)
            System.out.print(s.charAt(k++));
        System.out.println();
    }
}
