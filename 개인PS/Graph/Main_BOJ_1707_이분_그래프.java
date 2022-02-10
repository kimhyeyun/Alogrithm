package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1707_이분_그래프 {
    static int K, V, E;
    static boolean flag;
    static List<Integer>[] graph;
    static int[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            V = Integer.parseInt(stringTokenizer.nextToken());
            E = Integer.parseInt(stringTokenizer.nextToken());

            graph = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            isVisited = new int[V + 1];
            for (int i = 0; i < E; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(stringTokenizer.nextToken());
                int v = Integer.parseInt(stringTokenizer.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            for (int i = 1; i <= V; i++) {
                if(isVisited[i] == 0) BFS(i);
            }

            flag = false;
            Search();

            sb.append(flag ? "NO" : "YES").append("\n");
        }
        System.out.print(sb);
    }

    private static void Search() {
        for (int i = 1; i <= V; i++) {
            for (int next : graph[i]) {
                if (isVisited[i] == isVisited[next]) {
                    flag = true;
                    return;
                }
            }
        }
    }

    private static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph[now]) {
                if (isVisited[next] == 0) {
                    q.offer(next);
                    isVisited[next] = isVisited[now] == 1 ? 2 : 1;
                }
            }
        }
    }
}
