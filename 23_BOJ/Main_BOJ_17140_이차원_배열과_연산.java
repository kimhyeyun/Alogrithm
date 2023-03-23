import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17140_이차원_배열과_연산 {
    static class Pair implements Comparable<Pair>{
        int number, count;

        public Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count == o.count) {
                return this.number - o.number;
            }
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[101][101];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (arr[r][c] == k) {
            System.out.println(0);
            return;
        }

        int rowCount = 3, colCount = 3;
        int answer = 0;
        Map<Integer, Integer> map;

        while (answer++ <= 100) {
            if (rowCount >= colCount) {
                for (int i = 0; i < rowCount; i++) {
                    PriorityQueue<Pair> pq = new PriorityQueue<>();
                    map = new HashMap<>();

                    for (int j = 0; j < colCount; j++) {
                        if(arr[i][j] == 0) continue;
                        map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                    }

                    map.forEach((key, value) -> pq.add(new Pair(key, value)));

                    int idx = 0;
                    while (!pq.isEmpty()) {
                        Pair p = pq.poll();

                        arr[i][idx++] = p.number;
                        arr[i][idx++] = p.count;
                    }

                    colCount = Math.max(colCount, idx);
                    while (idx < 100) {
                        arr[i][idx++] = 0;
                    }
                }
            } else {
                for (int j = 0; j < colCount; j++) {
                    PriorityQueue<Pair> pq = new PriorityQueue<>();
                    map = new HashMap<>();

                    for (int i = 0; i < rowCount; i++) {
                        if(arr[i][j] == 0) continue;
                        map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                    }

                    map.forEach((key, value) -> pq.add(new Pair(key, value)));

                    int idx = 0;
                    while (!pq.isEmpty()) {
                        Pair p = pq.poll();

                        arr[idx++][j] = p.number;
                        arr[idx++][j] = p.count;
                    }

                    rowCount = Math.max(rowCount, idx);
                    while (idx < 100) {
                        arr[idx++][j] = 0;
                    }
                }
            }

            if (arr[r][c] == k) {
                System.out.println(answer);
                return;
            }
        }

        System.out.println(-1);
    }
}
