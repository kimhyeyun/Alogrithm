import java.util.*;

public class L3_합승_택시_요금 {
    static List<Edge>[] adj;

    static class Edge implements Comparable<Edge>{
        int index;
        int cost;

        public Edge(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        adj = new List[n+1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            adj[fare[0]].add(new Edge(fare[1], fare[2]));
            adj[fare[1]].add(new Edge(fare[0], fare[2]));
        }

        int[] costA = dijkstra(n, a);
        int[] costB = dijkstra(n, b);
        int[] costS = dijkstra(n, s);

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, costA[i] + costB[i] + costS[i]);
        }
        return answer;
    }

    private static int[] dijkstra(int n, int index) {
        int[] tmp = new int[n + 1];
        Arrays.fill(tmp, Integer.MAX_VALUE);

        Queue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(index, 0));
        tmp[index] = 0;

        while (!q.isEmpty()) {
            Edge now = q.poll();

            for (Edge next : adj[now.index]) {
                if (tmp[next.index] > tmp[now.index] + next.cost) {
                    tmp[next.index] = tmp[now.index] + next.cost;
                    q.add(next);
                }
            }
        }
        return tmp;
    }
}
