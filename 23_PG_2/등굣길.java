public class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;

        int[][] dp = new int[n + 1][m + 1];
        for (int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(dp[i][j] == -1) continue;

                if (dp[i - 1][j] > 0) dp[i][j] += dp[i - 1][j] % MOD;
                if (dp[i][j - 1] > 0) dp[i][j] += dp[i][j - 1] % MOD;
            }
        }

        return dp[n][m] % MOD;
    }
}
