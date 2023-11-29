import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_5972_택배_배송 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] map = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            map[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a].add(new int[]{b, c});
            map[b].add(new int[]{a, c});
        }

        for (int i = 0; i < N + 1; i++) {
            map[i].sort((Comparator.comparingInt(o -> o[1])));
        }

        System.out.println(DFS(N, map));
    }

    private static int DFS(int N, List<int[]>[] map) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] next : map[cur]) {
                if (dist[next[0]] > dist[cur] + next[1]) {
                    dist[next[0]] = dist[cur] + next[1];
                    q.add(next[0]);
                }
            }
        }
        return dist[N];
    }
}
