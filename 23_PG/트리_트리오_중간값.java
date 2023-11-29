import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 트리_트리오_중간값 {


    public int solution(int n, int[][] edges) {
        List<Integer>[] list = new List[n + 1];
        for (int i = 1; i <= n; i++) list[i] = new ArrayList<>();
        for (int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }

        int start = 1;
        int[] dist = BFS(list, start, n);
        for (int i = 2; i <= n; i++) {
            if(dist[i] > dist[start]) start = i;
        }

        int count = 0;
        dist = BFS(list, start, n);
        for (int i = 1; i <= n; i++) {
            if(dist[i] > dist[start]) start = i;
        }
        for (int i = 1; i <= n; i++) {
            if(dist[start] == dist[i]) count += 1;
        }
        if(count >= 2) return dist[start];

        count = 0;
        dist = BFS(list, start, n);
        for (int i = 1; i <= n; i++) {
            if(dist[i] > dist[start]) start = i;
        }
        for (int i = 1; i <= n; i++) {
            if(dist[start] == dist[i]) count += 1;
        }
        if(count >= 2) return dist[start];
        return dist[start] - 1;

    }

    private int[] BFS(List<Integer>[] list, int start, int n) {
        boolean[] isVisited = new boolean[n + 1];
        int[] dist = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list[cur]) {
                if(isVisited[next]) continue;

                isVisited[next] = true;
                q.add(next);
                dist[next] = dist[cur] + 1;
            }
        }

        return dist;
    }

    @Test
    void test() {
        assertEquals(solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}), 2);
        assertEquals(solution(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}), 2);
    }
}
