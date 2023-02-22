import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_24042_횡단보도 {
    static class CrossWalk implements Comparable<CrossWalk>{
        int loc;
        long dist;

        public CrossWalk(int loc, long dist) {
            this.loc = loc;
            this.dist = dist;
        }

        @Override
        public int compareTo(CrossWalk o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<CrossWalk>[] crossWalks = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            crossWalks[i] = new ArrayList<>();
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
        pq.offer(new CrossWalk(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            CrossWalk now = pq.poll();
            int curLoc = now.loc;
            long curDist = now.dist;

            if(dist[curLoc] < curDist) continue;
            for (CrossWalk next : crossWalks[curLoc]) {
                long nextDist;

                if (curDist <= next.dist) {
                    nextDist = next.dist + 1;
                } else {
                    nextDist = ((long) Math.ceil(((double) curDist - next.dist) / M)) * M + next.dist + 1;
                }
                if (nextDist < dist[next.loc]) {
                    dist[next.loc] = nextDist;
                    pq.offer(new CrossWalk(next.loc, nextDist));
                }
            }
        }
        System.out.println(dist[N]);
    }
}
