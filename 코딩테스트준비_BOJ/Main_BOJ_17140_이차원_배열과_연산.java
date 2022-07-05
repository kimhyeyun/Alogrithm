
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_17140_이차원_배열과_연산 {

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
            if(result == 0)
                result = this.num - o.num;

            return result;
        }
    }

    static int r, c, k;
    static int[][] A = new int[101][101];
    static int xLen = 3, yLen = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 1; i <= 3; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

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

    private static void C(int key) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= xLen; i++) {
            if(A[i][key] == 0) continue;
            map.compute(A[i][key], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }
        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[i++][key] = p.num;
            A[i++][key] = p.cnt;
        }

        xLen = Math.max(xLen, i);

        while (i <= 99) {
            A[i++][key] = 0;
            A[i++][key] = 0;
        }
    }

    private static void R(int key) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= yLen; i++) {
            if(A[key][i] == 0) continue;
            map.compute(A[key][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }
        map.forEach((k, v) -> pq.add(new pair(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            pair p = pq.poll();
            A[key][i++] = p.num;
            A[key][i++] = p.cnt;
        }

        yLen = Math.max(yLen, i);

        while(i <= 99){
            A[key][i++] = 0;
            A[key][i++] = 0;
        }

    }
}
