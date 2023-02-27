package class18;

/**
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class Code01_RobotWalk {

    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }

        return process(start, K, aim, N);
    }

    /**
     * @param cur  机器人当前来到的位置是cur，
     * @param rest 机器人还有rest步需要去走，
     * @param aim  最终的目标是aim，
     * @param N    有哪些位置？1~N
     * @return 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
     */
    public static int process(int cur, int rest, int aim, int N) {
        //已经没有步数要走了，走完了，判断当前位置是否等于目标位置，如果是则代表这条
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return process(N - 1, rest - 1, aim, N);
        }
        return process(cur - 1, rest - 1, aim, N) + process(cur + 1, rest - 1, aim, N);
    }

    public static int ways3(int N, int start, int aim, int K) {
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
