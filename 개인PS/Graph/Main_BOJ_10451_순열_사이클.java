package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_10451_순열_사이클 {
    static int T, N;
    static List<Integer>[] graph;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            graph = new List[N + 1];
            isVisited = new boolean[N + 1];

            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            stringTokenizer = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int x = Integer.parseInt(stringTokenizer.nextToken());
                graph[i].add(x);
                graph[x].add(i);
            }

            int ans = 0;
            for (int i = 1; i <= N; i++) {
                if (!isVisited[i]) {
                    BFS(i);
                    ans += 1;
                }
            }
            System.out.println(ans);
        }
    }

    private static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
