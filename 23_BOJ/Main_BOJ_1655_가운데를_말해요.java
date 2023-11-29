import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1655_가운데를_말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq_min = new PriorityQueue<>();
        PriorityQueue<Integer> pq_max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            pq_min.add(x);

            if ((pq_min.size() + pq_max.size()) % 2 != 0) {
                pq_max.add(pq_min.poll());
            } else {
                if (x < pq_max.peek()) {
                    pq_min.add(pq_max.poll());
                    pq_max.add(pq_min.poll());
                }
            }

            sb.append(pq_max.peek()).append("\n");
        }
        System.out.println(sb);

    }
}
