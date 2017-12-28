package 动态规划;

/**
 * Created by WQS on 2017/9/21.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

/**
 * 最长子非降序列长度
 * 子序列:不要求连续,只需要相对位置即可
 */
public class 最长非降子序列长度 {
    public static void main(String[] args) {
        /**
         * d(i)表示前i个元素的最长非降子序列长度
         * d(1) = 1
         * d(2) = d(1) + 1 if A[2] >= A[1] else 1
         * ...
         * d(i) = max{1, d(j) + 1 | j = 1,2....i-1 && A[j] <= A[i] }
         */

        // 第一个元素不参与
        int[] A = {Integer.MAX_VALUE, 5, 3, 4, 8, 6, 7};
        int[] dp = new int[A.length];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = 1;
        }

        dp[1] = 1;
        for (int i = 2; i < A.length; ++i) {
            for (int j = 1; j < i; ++j) {
                if (A[j] <= A[i])
                    dp[i] = Math.max(1, dp[j] + 1);
            }
        }

        for (int i = 1; i < dp.length; ++i) {
            System.out.println("前" + i + "个元素的最长非降子序列长度为" + dp[i]);
        }
    }
}
