import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1655_가운데를_말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq_max = new PriorityQueue<>();
        PriorityQueue<Integer> pq_min = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            pq_max.add(x);
            if ((pq_max.size() + pq_min.size()) % 2 != 0) {
                pq_min.add(pq_max.poll());
            } else {
                if (x < pq_min.peek()) {
                    pq_max.add(pq_min.poll());
                    pq_min.add(pq_max.poll());
                }
            }
            sb.append(pq_min.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
