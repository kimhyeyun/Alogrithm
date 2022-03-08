import java.util.*;

public class 스킬_체크_테스트_1 {
    public static int solution(int n, int[][] edge) {
        int answer = 0;

        List<Integer>[] adj = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        dist[1] = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : adj[now]) {
                if (dist[next] > dist[now] + 1) {
                    dist[next] = dist[now] + 1;
                    q.add(next);
                }
            }
        }

        int max = 0;
        for (int i = 2; i < n + 1; i++) {
            max = max < dist[i] ? dist[i] : max;
        }

        for (int i = 2; i < n + 1; i++) {
            if(dist[i] == max) answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(6, vertex));
    }
}
