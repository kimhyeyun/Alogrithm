import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_1727_커플_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] men = new int[n];
        int[] women = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(men);
        Arrays.sort(women);

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int diff = Math.abs(men[i - 1] - women[j - 1]);
                if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + diff;
                } else if (i > j) {

                    dp[i][j] = Math.min(dp[i - 1][j - 1] + diff, dp[i - 1][j]);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + diff, dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
