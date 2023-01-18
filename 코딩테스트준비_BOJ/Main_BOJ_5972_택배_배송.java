import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_5972_택배_배송 {
    static int N, M;
    static List<int[]>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        adj = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            adj[x].add(new int[]{y, w});
            adj[y].add(new int[]{x, w});
        }

        System.out.println(dijkstra(1));
    }

    private static int dijkstra(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] min = new int[N + 1];
        Arrays.fill(min, Integer.MAX_VALUE);

        min[start] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int[] next : adj[now]) {
                if (min[next[0]] > min[now] + next[1]) {
                    min[next[0]] = min[now] + next[1];
                    q.add(next[0]);
                }
            }
        }

        return min[N];
    }
}
