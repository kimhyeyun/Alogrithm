package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11724_연결_요소의_개수 {
    static int N, M;
    static boolean[][] graph;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        graph = new boolean[N + 1][N + 1];
        isVisited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            graph[u][v] = true;
            graph[v][u] = true;
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if(!isVisited[i]){
                DFS(i);
                ans += 1;
            }
        }
        System.out.println(ans);
    }

    private static void DFS(int start) {
        isVisited[start] = true;
        for (int i = 1; i <= N; i++) {
            if(!isVisited[i] && graph[start][i]) DFS(i);
        }
        return;
    }
}
