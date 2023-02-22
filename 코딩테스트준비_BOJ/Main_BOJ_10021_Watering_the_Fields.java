import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_10021_Watering_the_Fields {
    static class Node {
        int start;
        int end;
        long value;

        public Node(int start, int end, long value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    static int N, C;
    static int[][] coord;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        coord = new int[N][2];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            coord[i][0] = Integer.parseInt(st.nextToken());
            coord[i][1] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> (int) (o1.value - o2.value)));

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                long dist = (long) (Math.pow((coord[i][0] - coord[j][0]), 2)) + (long) (Math.pow((coord[i][1] - coord[j][1]), 2));

                pq.offer(new Node(i, j, dist));
            }
        }

        int sum = 0, count = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.value < C) continue;

            int start = now.start;
            int end = now.end;

            if (!isSameParent(start, end)) {
                sum += now.value;
                union(start, end);
                count += 1;
            }

            if (count == N - 1) break;
        }

        if (count != N - 1) {
            System.out.println(-1);
            return;
        }

        System.out.println(sum);
    }

    private static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start != end) {
            parent[end] = start;
        }
    }

    private static boolean isSameParent(int start, int end) {
        start = find(start);
        end= find(end);

        if (start == end) return true;
        return false;
    }

    private static int find(int coord) {
        if (coord == parent[coord]) {
            return coord;
        }
        return find(parent[coord]);
    }
}
