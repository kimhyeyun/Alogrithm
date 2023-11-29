import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1562_계단_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long answer = 0;
        long[][][] dp = new long[N + 1][10][1 << 10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    int cur = k | (1 << j);
                    if(j == 0) dp[i][j][cur] += dp[i - 1][j + 1][k] % 1_000_000_000;
                    else if(j == 9) dp[i][j][cur] += dp[i - 1][j - 1][k] % 1_000_000_000;
                    else dp[i][j][cur] += (dp[i - 1][j - 1][k] % 1_000_000_000 + dp[i - 1][j + 1][k] % 1_000_000_000);

                    dp[i][j][cur] %= 1_000_000_000;
                }
            }
        }

        for (int k = 0; k <= 9; k++) {
            answer += dp[N][k][(1 << 10) - 1] % 1_000_000_000;
            answer %= 1_000_000_000;
        }

        System.out.println(answer);
    }

}
