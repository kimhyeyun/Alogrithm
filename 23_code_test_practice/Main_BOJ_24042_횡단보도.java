import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_24042_횡단보도 {
    private static class CrossWalk implements Comparable<CrossWalk> {
        int loc;
        long weight;

        public CrossWalk(int loc, long weight) {
            this.loc = loc;
            this.weight = weight;
        }

        @Override
        public int compareTo(CrossWalk o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<CrossWalk>[] crossWalks = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            crossWalks[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            crossWalks[u].add(new CrossWalk(v, i));
            crossWalks[v].add(new CrossWalk(u, i));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<CrossWalk> pq = new PriorityQueue<>();
        pq.add(new CrossWalk(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            CrossWalk cur = pq.poll();

            if (dist[cur.loc] < cur.weight) continue;

            for (CrossWalk next : crossWalks[cur.loc]) {
                long nextWeight;
                if(cur.weight <= next.weight) nextWeight = next.weight + 1;
                else nextWeight = (long) ((Math.ceil((double) (cur.weight - next.weight) / M)) * M + next.weight + 1);

                if (nextWeight < dist[next.loc]) {
                    dist[next.loc] = nextWeight;
                    pq.add(new CrossWalk(next.loc, nextWeight));
                }
            }
        }
        System.out.println(dist[N]);
    }
}
