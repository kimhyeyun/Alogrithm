package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1260_DFS와_BFS {
    static int N, M, V;
    static boolean[][] graph;
    static boolean[] isVisited;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        V = Integer.parseInt(stringTokenizer.nextToken());

        isVisited = new boolean[N + 1];
        graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            graph[x][y] = true;
            graph[y][x] = true;
        }

        DFS(V);
        sb.append("\n");
        BFS(V);

        System.out.println(sb);
    }

    private static void BFS(int v) {
        isVisited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        isVisited[V] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now + " ");
            for (int i = 1; i <= N; i++) {
                if (!isVisited[i] && graph[now][i]) {
                    q.add(i);
                    isVisited[i] = true;
                }
            }
        }
    }

    private static void DFS(int start) {
        sb.append(start + " ");
        isVisited[start] = true;
        for (int i = 1; i <= N; i++) {
            if (!isVisited[i] && graph[start][i])
                DFS(i);
        }
        return;
    }
}
