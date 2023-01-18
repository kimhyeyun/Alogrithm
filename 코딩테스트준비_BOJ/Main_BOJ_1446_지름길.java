import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1446_지름길 {
    static int N, D;
    static ShortCut[] shortCuts;

    static class ShortCut implements Comparable<ShortCut> {
        int start, end, weight;

        public ShortCut(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(ShortCut o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());
        shortCuts = new ShortCut[N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int dist = Integer.parseInt(stringTokenizer.nextToken());

            shortCuts[i] = new ShortCut(start, end, dist);
        }

        System.out.println(dijkstra(0));

    }

    private static int dijkstra(int start) {
        PriorityQueue<ShortCut> q = new PriorityQueue<>();
        int[] dist = new int[D + 1];
        for (int i = 0; i < D + 1; i++) {
            dist[i] = i;
        }

        q.offer(new ShortCut(start, start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            ShortCut now = q.poll();

            for (ShortCut next : shortCuts) {
                if (next.start >= now.end) {
                    if (next.end > D) {
                        continue;
                    }
                    int tmpDis = next.start - now.end;
                    if (dist[next.end] > dist[now.end] + next.weight + tmpDis) {
                        dist[next.end] = dist[now.end] + next.weight + tmpDis;
                        q.add(new ShortCut(now.end, next.end, dist[next.end]));
                    }
                }
            }

            dist[D] = Math.min(dist[now.end] + D - now.end, dist[D]);
        }
        return dist[D];
    }
}
