import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L3_부대복귀 {
    List<Integer>[] adj;
    int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        adj = new List[n + 1];
        for (int i = 0; i < n + 1; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < roads.length; i++) {
            adj[roads[i][0]].add(roads[i][1]);
            adj[roads[i][1]].add(roads[i][0]);
        }

        dist = new int[n + 1];
        for (int i = 0; i < n + 1; i++) dist[i] = Integer.MAX_VALUE;

        dijkstra(destination);

        for (int i = 0; i < sources.length;i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }
        return answer;
    }

    private void dijkstra(int destination) {
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        dist[destination] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : adj[now]) {
                if(dist[next] <= dist[now] + 1) continue;

                dist[next] = dist[now] + 1;
                q.add(next);
            }
        }
    }
}
