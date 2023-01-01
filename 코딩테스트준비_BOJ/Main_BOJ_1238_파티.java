import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1238_파티 {
    static int N, M, X;
    static List<int[]>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        X = Integer.parseInt(stringTokenizer.nextToken());

        adj = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            adj[start].add(new int[]{end, weight});
        }

        int answer = Integer.MIN_VALUE;
        int[] dist = findWeightFromX(X);
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, findWeight(i) + dist[i]);
        }
        System.out.println(answer);
    }

    private static int[] findWeightFromX(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int[] next : adj[now]) {
                if (dist[next[0]] <= dist[now] + next[1]) {
                    continue;
                }
                q.add(next[0]);
                dist[next[0]] = dist[now] + next[1];
            }
        }
        return dist;
    }

    private static int findWeight(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int[] next : adj[now]) {
                if (dist[next[0]] <= dist[now] + next[1]) {
                    continue;
                }
                q.add(next[0]);
                dist[next[0]] = dist[now] + next[1];
            }
        }
        return dist[X];
    }
}
