import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n_17140_이차원_배열과_연산 {
    static class pair implements Comparable<pair>{
        int num;
        int cnt;

        public pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(pair o) {
            int result = this.cnt - o.cnt;
            if(result == 0) result = this.num - o.num;

            return result;
        }
    }
    static final int MAX = 101;
    static int r, c, k, xLen, yLen;
    static int[][] A;
    static PriorityQueue<pair> pq;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        A = new int[MAX][MAX];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        xLen = yLen = 3;
        System.out.println(solution());
    }

    private static int solution() {
        for (int time = 0; time <= 100; time++) {
            if(A[r][c] == k) return time;

            operating();
        }
        return -1;
    }

    private static void operating() {
        if (xLen >= yLen) {
            for (int i = 0; i < xLen; i++) {
                R(i);
            }
        }else{
            for (int i = 0; i < yLen; i++) {
                C(i);
            }
        }
    }

    private static void C(int y) {
        pq = new PriorityQueue<>();
        map = new HashMap<>();

        for (int x = 0; x < xLen; x++) {
            if(A[x][y] == 0) continue;
            map.put(A[x][y], map.getOrDefault(A[x][y], 0) + 1);
        }

        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int idx = 0;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[idx++][y] = p.num;
            A[idx++][y] = p.cnt;
        }

        xLen = Math.max(xLen, idx);
        while (idx < 100) {
            A[idx++][y] = 0;
            A[idx++][y] = 0;
        }
    }

    private static void R(int x) {
        pq = new PriorityQueue<>();
        map = new HashMap<>();

        for (int y = 0; y < yLen; y++) {
            if(A[x][y] == 0) continue;
            map.put(A[x][y], map.getOrDefault(A[x][y], 0) + 1);
        }

        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int idx = 0;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[x][idx++] = p.num;
            A[x][idx++] = p.cnt;
        }

        yLen = Math.max(yLen, idx);
        while (idx <= 99) {
            A[x][idx++] = 0;
            A[x][idx++] = 0;
        }
    }
}
