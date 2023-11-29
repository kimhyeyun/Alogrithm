import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1446_지름길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ShortCut[] shortCuts = new ShortCut[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            shortCuts[i] = new ShortCut(s, e, d);
        }

        System.out.println(dijkstra(D, 0, shortCuts));
    }

    private static int dijkstra(int D, int start, ShortCut[] shortCuts) {
        PriorityQueue<ShortCut> pq = new PriorityQueue<>();
        int[] dist = new int[D + 1];
        for (int i = 0; i <= D; i++) {
            dist[i] = i;
        }

        pq.offer(new ShortCut(start, start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            ShortCut cur = pq.poll();

            for (ShortCut next : shortCuts) {
                if (cur.end <= next.start) {
                    if(D < next.end) continue;

                    int tmpDist = next.start - cur.end;

                    if(dist[next.end] <= dist[cur.end] + next.dist + tmpDist) continue;

                    dist[next.end] = dist[cur.end] + next.dist + tmpDist;
                    pq.add(new ShortCut(cur.end, next.end, dist[next.end]));
                }
            }

            dist[D] = Math.min(dist[cur.end] + D - cur.end, dist[D]);
        }
        return dist[D];
    }


    private static class ShortCut implements Comparable<ShortCut>{
        int start, end;
        int dist;

        public ShortCut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(ShortCut o) {
            return this.dist - o.dist;
        }
    }
}
