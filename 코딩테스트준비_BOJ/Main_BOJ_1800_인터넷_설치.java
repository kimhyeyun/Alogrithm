import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1800_인터넷_설치 {
    static int N, P, K;
    static List<Line>[] lines;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        P = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        lines = new List[N + 1];
        cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            lines[i] = new ArrayList<>();
        }

        int max = 0;

        for (int i = 0; i < P; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            lines[s].add(new Line(e, c));
            lines[e].add(new Line(s, c));

            max = Math.max(max, c);
        }

        int start = 0;
        int answer = Integer.MIN_VALUE;

        while (start <= max) {
            int mid = (start + max) / 2;

            if (dijkstra(mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (answer == Integer.MIN_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static boolean dijkstra(int x) {
        PriorityQueue<Line> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.MAX_VALUE;
        }

        cost[1] = 0;
        pq.offer(new Line(1, 0));

        while (!pq.isEmpty()) {
            Line now = pq.poll();

            if(cost[now.index] < now.cost) continue;

            for (Line next : lines[now.index]) {
                int nextCost = now.cost;

                if (next.cost > x) nextCost += 1;
                if (nextCost < cost[next.index]) {
                    cost[next.index] = nextCost;
                    pq.offer(new Line(next.index, nextCost));
                }
            }
        }
        return cost[N] <= K;
    }

    private static class Line implements Comparable<Line>{
        int index;
        int cost;

        public Line(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return this.cost - o.cost;
        }
    }
}
