import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_17140_이차원_배열과_연산_re {
    static class pair implements Comparable<pair>{
        int num, cnt;

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
    static int r, c, k, xLen, yLen;
    static final int MAX = 101;
    static PriorityQueue<pair> pq;
    static Map<Integer, Integer> map;
    static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        A = new int[MAX][MAX];
        for (int i = 1; i <= 3; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
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
            for (int i = 1; i <= xLen; i++) R(i);
        } else {
            for (int i = 1; i <= yLen; i++) C(i);
        }
    }

    private static void C(int col) {
        pq = new PriorityQueue<>();
        map = new HashMap<>();

        for (int i = 1; i <= xLen; i++) {
            if(A[i][col] == 0) continue;
            map.compute(A[i][col], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }
        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[i++][col] = p.num;
            A[i++][col] = p.cnt;
        }

        xLen = Math.max(xLen, i);

        while (i <= 99) {
            A[i++][col] = 0;
            A[i++][col] = 0;
        }
    }

    private static void R(int row) {
        pq = new PriorityQueue<>();
        map = new HashMap<>();

        for (int i = 1; i <= yLen; i++) {
            if(A[row][i] == 0) continue;
            map.compute(A[row][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[row][i++] = p.num;
            A[row][i++] = p.cnt;
        }

        yLen = Math.max(yLen, i);

        while (i <= 99) {
            A[row][i++] = 0;
            A[row][i++] = 0;
        }
    }
}
