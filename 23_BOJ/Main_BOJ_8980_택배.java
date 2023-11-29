import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_8980_택배 {
    static class Delivery implements Comparable<Delivery> {
        int start;
        int end;
        int count;

        public Delivery(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public int compareTo(Delivery o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            deliveries.add(new Delivery(s, e, c));
        }

        Collections.sort(deliveries);

        int[] possible = new int[N + 1];
        for (int i = 1; i < N; i++) {
            possible[i] = C;
        }

        int maxCountOfBoxes = Integer.MAX_VALUE;
        int answer = 0;
        for (Delivery delivery : deliveries) {
            maxCountOfBoxes = Integer.MAX_VALUE;

            for (int i = delivery.start; i < delivery.end; i++) {
                maxCountOfBoxes = Math.min(maxCountOfBoxes, possible[i]);
            }

            if (maxCountOfBoxes <= delivery.count) {
                for (int i = delivery.start; i < delivery.end; i++) {
                    possible[i] -= maxCountOfBoxes;
                }

                answer += maxCountOfBoxes;
            } else {
                for (int i = delivery.start; i < delivery.end; i++) {
                    possible[i] -= delivery.count;
                }

                answer += delivery.count;
            }
        }

        System.out.println(answer);
    }
}
