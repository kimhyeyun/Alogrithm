import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_8980_택배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        List<int[]> deliveries = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            deliveries.add(new int[]{from, to, count});
        }

        deliveries.sort(((o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        }));

        int[] possible = new int[N + 1];
        for (int i = 1; i < N; i++) {
            possible[i] = C;
        }

        int maxCountOfBoxes = Integer.MAX_VALUE;
        int answer = 0;
        for (int[] delivery : deliveries) {
            maxCountOfBoxes = Integer.MAX_VALUE;

            for (int i = delivery[0]; i < delivery[1]; i++) {
                maxCountOfBoxes = Math.min(maxCountOfBoxes, possible[i]);
            }

            if (maxCountOfBoxes <= delivery[2]) {
                for (int i = delivery[0]; i < delivery[1]; i++) {
                    possible[i] -= maxCountOfBoxes;
                }
                answer += maxCountOfBoxes;
            } else {
                for (int i = delivery[0]; i < delivery[1]; i++) {
                    possible[i] -= delivery[2];
                }
                answer += delivery[2];
            }
        }
        System.out.println(answer);
    }
}
