import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class L3_보행자_천국 {
    public static int solution(int m, int n, int[][] cityMap) {
        int MOD = 20170805;
        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1; // 0 -> 위에서
        dp[0][0][1] = 1; // 1 -> 왼쪽에서

        for (int i = 1; i < m; i++) {
            if (cityMap[i][0] == 1) {
                continue;
            }
            dp[i][0][0] = dp[i - 1][0][0];
        }

        for (int i = 1; i < n; i++) {
            if (cityMap[0][i] == 1) {
                continue;
            }
            dp[0][i][1] = dp[0][i - 1][1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(cityMap[i-1][j] != 1) {
                    if (cityMap[i - 1][j] == 2) {
                        dp[i][j][0] = (dp[i - 1][j][0] % MOD);
                    } else {
                        dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                    }
                }

                if(cityMap[i][j-1] != 1) {
                    if (cityMap[i][j - 1] == 2) {
                        dp[i][j][1] = (dp[i][j - 1][1] % MOD);
                    } else {
                        dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                    }
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    @Test
    void test() {
        assertEquals(2, solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }
}
