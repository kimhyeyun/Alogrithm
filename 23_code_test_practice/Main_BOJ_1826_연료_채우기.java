import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1826_연료_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> stations = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stations.add(new int[]{x, y});
        }

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());


        PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;

        while (P < L) {
            while (!stations.isEmpty() && stations.peek()[0] <= P) fuels.add(stations.poll()[1]);

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
