import java.util.HashSet;
import java.util.Set;

public class L2_멀리_뛰기 {
    public static long solution(int n) {
        if(n == 1) return 1;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1234567;
        }

        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(solution(1));
    }
}
