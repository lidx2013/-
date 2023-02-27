package class19;

/**
 * 背包问题
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 */
public class Code01_Knapsack {


    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    private static int process(int[] w, int[] v, int index, int rest) {

        //小于代表无效
        if (rest < 0) {
            return -1;
        }

        if (index == w.length) {
            return 0;
        }

        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = next + v[index];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];

    }


}
