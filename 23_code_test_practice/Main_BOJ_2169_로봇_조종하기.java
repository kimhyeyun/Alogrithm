import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2169_로봇_조종하기 {
    static final int[] dx = {0, 1, 0};
    static final int[] dy = {1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];
        dp[0][0] = map[0][0];

        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        for (int i = 1; i < N; i++) {
            int[] temp1 = new int[M];
            int[] temp2 = new int[M];

            // 왼쪽 또는 위에서 오는 경우
            temp1[0] = dp[i - 1][0] + map[i][0];
            for (int j = 1; j < M; j++) {
                temp1[j] = Math.max(dp[i - 1][j], temp1[j - 1]) + map[i][j];
            }

            temp2[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                temp2[j] = Math.max(dp[i - 1][j], temp2[j + 1]) + map[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp1[j], temp2[j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);

    }

}
