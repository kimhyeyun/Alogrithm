import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1826_연료_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<int[]> stations = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            stations.add(new int[]{a, b});
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());

        int answer = 0;

        while (P < L) {
            while (!stations.isEmpty() && stations.peek()[0] <= P) {
                fuels.add(stations.poll()[1]);
            }

            if (fuels.isEmpty()) {
                System.out.println(-1);
                return;
            }

            answer += 1;
            P += fuels.poll();
        }

        System.out.println(answer);
    }
}
