package ch60;

/**
 * Created by WQS on 2017/8/15.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import java.util.Arrays;

/**
 * 后缀数组
 */
public class SuffixArray {

    private String[] suffix;
    private int N;  //输入的文本字符串的长度

    public SuffixArray(String text) {
        N = text.length();
        suffix = new String[N];

        for (int i = 0; i < N; ++i)
            suffix[i] = text.substring(i);

        sort(suffix, 0, N - 1);
    }

    /**
     * 返回后缀数组第i个元素,i的范围为[0...N-1]
     *
     * @param index
     * @return
     */
    String select(int index) {
        return suffix[index];
    }

    /**
     * 返回select(i)的索引,返回值范围为[0...N-1]
     *
     * @param i
     * @return
     */
    int index(int i) {
        return N - select(i).length();
    }

    /**
     * 返回select(i)和select(i-1)的最长公共前缀的长度,输入i的范围为[1...N-1]
     *
     * @param i
     * @return
     */
    int lcp(int i) {
        int len = Math.min(select(i).length(), select(i - 1).length());
        for (int k = 0; k < len; ++k)
            if (select(i).charAt(k) != select(i - 1).charAt(k))
                return k;
        return len;
    }

    /**
     * 返回小于键key的后缀数量
     *
     * @param key
     * @return
     */
    int rank(String key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = suffix[mid].compareTo(key);
            if (cmp < 0)
                lo = mid + 1;
            else if (cmp > 0)
                hi = mid - 1;
            else
                return mid;
        }
        return lo;
    }

    /**
     * 返回输入的字符串的长度
     *
     * @return
     */
    public int length() {
        return N;
    }

    private void sort(String[] suffix, int lo, int hi) {
        if (lo > hi)
            return;

        int lt = lo, gt = hi;
        int i = lt + 1;
        String key = suffix[lo];
        while (i <= gt) {
            int cmp = suffix[i].compareTo(key);
            if (cmp < 0) {
                exchange(suffix, i++, lt++);
            } else if (cmp > 0) {
                exchange(suffix, i, gt--);
            } else {
                ++i;
            }
        }
        sort(suffix, lo, lt - 1);
        sort(suffix, gt + 1, hi);
    }

    private void exchange(String[] suffix, int i, int j) {
        String t = suffix[i];
        suffix[i] = suffix[j];
        suffix[j] = t;
    }

    public static void main(String[] args) {
        String s = "it was the best of times it was the";
        SuffixArray sa = new SuffixArray(s);
    }
}
