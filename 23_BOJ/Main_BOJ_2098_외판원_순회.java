import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2098_외판원_순회 {
    static int N;
    static int[][] graph;
    static int[][] dp;
    static final int INF = 16_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(DFS(0, 1));
    }

    private static int DFS(int now, int visit) {
        if (visit == (1 << N) - 1) {
            if(graph[now][0] == 0) return INF;
            return graph[now][0];
        }

        if(dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = INF;

        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && graph[now][i] != 0) {
                dp[now][visit] = Math.min(DFS(i, visit | (1 << i)) + graph[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }
}
