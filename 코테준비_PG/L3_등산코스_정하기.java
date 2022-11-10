import java.util.*;

public class L3_등산코스_정하기 {

    static List<int[]>[] graph;
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int time = path[2];

            if (isGate(start, gates) || isSummit(end, summits)) {
                graph[start].add(new int[]{end, time});
            } else if (isGate(end, gates) || isSummit(start, summits)) {
                graph[end].add(new int[]{start, time});
            } else {
                graph[start].add(new int[]{end, time});
                graph[end].add(new int[]{start, time});
            }
        }

        return dijkstra(n, gates, summits);
    }

    private static int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<int[]> q = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            q.add(new int[]{gate, 0});
            intensity[gate] = 0;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if(now[1] > intensity[now[0]]) continue;

            for (int[] next : graph[now[0]]) {
                int dist = Math.max(intensity[now[0]], next[1]);

                if (intensity[next[0]] > dist) {
                    intensity[next[0]] = dist;
                    q.add(new int[]{next[0], dist});
                }
            }
        }

        int minSummitNum = Integer.MAX_VALUE;
        int minDist = Integer.MAX_VALUE;

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minDist) {
                minDist = intensity[summit];
                minSummitNum = summit;
            }
        }

        return new int[]{minSummitNum, minDist};
    }

    private static boolean isSummit(int num, int[] summits) {
        for (int summit : summits) {
            if (summit == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean isGate(int num, int[] gates) {
        for (int gate : gates) {
            if (gate == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};
        int[] answer = solution(6, paths, gates, summits);

        for (int a : answer) {
            System.out.println(a);
        }


    }
}
