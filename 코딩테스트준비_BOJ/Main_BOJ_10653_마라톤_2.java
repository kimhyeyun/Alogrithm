import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_10653_마라톤_2 {
    static int N, K;
/*    static int answer;
    static List<int[]> points;
    static int[] endPoint;*/

    static int[][] dp;
    static int[][] dist;
    static List<int[]> points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new int[]{x, y});
//            if(i == N - 1) endPoint = new int[]{x, y};
        }

        dp = new int[N][K + 1];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                dist[i][j] = Math.abs(points.get(i)[0] - points.get(j)[0]) + Math.abs(points.get(i)[1] - points.get(j)[1]);
                dist[j][i] = Math.abs(points.get(i)[0] - points.get(j)[0]) + Math.abs(points.get(i)[1] - points.get(j)[1]);
            }
        }

        System.out.println(DFS(N - 1, K));

//        DFS(0,1, 0, 0);

//        System.out.println(answer);
    }

    private static int DFS(int idx, int k) {
        if(idx == 0) return 0;
        if(dp[idx][k] != -1) return dp[idx][k];

        int d = Integer.MAX_VALUE;

        for (int i = 0; i <= k; i++) {
            if (idx - i - 1 < 0) break;
            d = Math.min(DFS(idx - i - 1, k - i) + dist[idx - i - 1][idx], d);
        }

        return dp[idx][k] = d;
    }

    /* DFS -> 시간 초과*/
/*    private static void DFS(int nowIdx, int nextIdx, int dist, int cnt) {
        if(nextIdx == N){
            if(K == cnt)
                answer = Math.min(answer, dist);

            return;
        }

        int[] now = points.get(nowIdx);
        int[] next = points.get(nextIdx);
        int d = Math.abs(now[0] - next[0]) + Math.abs(now[1] - next[1]);

        // 건너뛰는 경우
        if(nextIdx != N-1 && cnt < K) DFS(nowIdx, nextIdx + 1, dist, cnt + 1);

        // 들리는 경우
        DFS(nextIdx, nextIdx + 1, dist + d, cnt);

        return;
    }*/
}
