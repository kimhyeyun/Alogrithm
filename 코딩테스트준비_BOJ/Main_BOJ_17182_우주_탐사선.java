import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17182_우주_탐사선 {
    static int N, K, answer;
    static int[][] times;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        times = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                times[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(k == i) continue;
                for (int j = 0; j < N; j++) {
                    if(i == j || k == j) continue;

                    times[i][j] = Math.min(times[i][j], times[i][k] + times[k][j]);
                }
            }
        }

        answer = Integer.MAX_VALUE;

        isVisited = new boolean[N];
        isVisited[K] = true;

        DFS(K, 0, 0);

        System.out.println(answer);
    }

    private static void DFS(int now, int sum, int depth) {
        if (depth == N - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isVisited[i]) continue;

            isVisited[i] = true;
            DFS(i, sum + times[now][i], depth + 1);
            isVisited[i] = false;
        }
    }
}
