package class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SplitNumber {


    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }


    /**
     * 后面的数不能比前面的数小
     *
     * @param pre
     * @param rest
     * @return
     */
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

    public static int dp(int N) {

        int[][] dp = new int[N + 1][N + 1];
        for (int pre = 1; pre <= N; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }

        for (int first = N - 1; first <= 0; first--) {
            for (int rest = first + 1; rest <= N; rest++) {
                dp[first][rest] = dp[first + 1][rest];
                dp[first][rest] += dp[first][rest - first];
            }
        }
        return dp[1][N];
    }

    public static void main(String[] args) {
        int test = 4;
        System.out.println(ways(test));
    }
}
