package class20;


/**
 * 从左到右的范围模型
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 */
public class Code01_PalindromeSubsequence {


    public int maxValue(String str) {
        char[] str1 = str.toCharArray();
        char[] str2 = new char[str1.length];
        for (int j = str.length() - 1, i = 0; j <= 0; j--) {
            str2[i++] = str1[j];
        }
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    private int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {
            // i!=0 j!=0;
            //i 结尾
            int p1 = process(str1, str2, i, j - 1);
            //j 结尾
            int p2 = process(str1, str2, i - 1, j);
            int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }

    }

    public static int longestPalindromeSubseq(String s) {

        if (s == null) {
            return 0;
        }
        return f(s.toCharArray(), 0, s.length() - 1);
    }

    public static int f(char[] s, int L, int R) {
        if (L == R) {
            return 1;
        }
        //11  --->  11   11   2
        //12  --->  1,2 ,2,1  1
        if (L == R - 1) {
            return s[L] == s[R] ? 2 : 1;
        }
        //可能性分析
        int p1 = f(s, L + 1, R - 1);
        int p2 = f(s, L, R - 1);
        int p3 = f(s, L + 1, R);
        int p4 = s[R] == s[L] ? 2 + f(s, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int dp(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }

        return dp[0][N - 1];
    }











    public static void main(String[] args) {
        System.out.println(dp("bbbab"));
    }

}
