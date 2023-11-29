import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1238_파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<int[]>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[x].add(new int[]{y, t});
        }

        for (int i = 1; i <= N; i++) {
            adj[i].sort((Comparator.comparingInt(o -> o[1])));
        }

        int[] XtoHome = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if(i == X) continue;
            XtoHome[i] = BFS(X, i, N, adj);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if(i == X) continue;
            answer = Math.max(answer, BFS(i, X, N, adj) + XtoHome[i]);
        }

        System.out.println(answer);
    }

    private static int BFS(int start, int dest, int N, List<int[]>[] adj) {
        Queue<int[]> q = new LinkedList<>();
        int[] time = new int[N + 1];

        Arrays.fill(time, Integer.MAX_VALUE);

        q.add(new int[]{start, 0});
        time[start] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] next : adj[cur[0]]) {
                if(time[next[0]] <= cur[1] + next[1]) continue;

                time[next[0]] = cur[1] + next[1];
                q.add(new int[]{next[0], cur[1] + next[1]});
            }
        }
        return time[dest];
    }
}
