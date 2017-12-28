package 动态规划;

/**
 * Created by WQS on 2017/9/21.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class 硬币问题 {
    public static void main(String[] args) {
        /**
         * 如果有面值为1 3 5的硬币,如何用最少的硬币凑齐11元
         * 令d(i)表示凑齐i元所需要的最小硬币
         * 则d(i) = min{d(i-vj)+1 | j=1,2,3....} vj表示第j个硬币的价值
         */

        int[] dp = new int[12]; //dp[i]表示凑齐i元最少需要多少个硬币
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = Integer.MAX_VALUE / 2;
        }
        dp[0] = 0;

        //int[] v = new int[]{2};
        int[] v = new int[]{1, 3, 5};
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 0; j < v.length; ++j) {
                if (i - v[j] >= 0 && dp[i - v[j]] + 1 < dp[i]) {
                    dp[i] = dp[i - v[j]] + 1;
                }
            }
        }

        for (int i = 0; i < dp.length; ++i) {
            // 如果输出中有Inter.MAX_VALUE的话,表明无法凑齐i元
            System.out.println("凑齐" + i + "元至少需要" + dp[i] + "个硬币");
        }
    }
}
