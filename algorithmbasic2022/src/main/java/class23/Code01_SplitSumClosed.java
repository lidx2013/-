package class23;

import base.GenerateRandom;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class Code01_SplitSumClosed extends GenerateRandom {

    public static int right(int[] N) {
        double average = Arrays.stream(N).asDoubleStream().average().orElse(0);
        return process(N, 0, average);
    }


    public static int process(int[] N, int index, double rest) {

        if (index == N.length) {
            return 0;
        }
        int p1 = process(N, index + 1, rest);

        int p2 = 0;
        if (N[index] <= rest) {
            p2 = N[index] + process(N, index + 1, rest - N[index]);
        }

        return Math.max(p1, p2);
    }


    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }


    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }


}
