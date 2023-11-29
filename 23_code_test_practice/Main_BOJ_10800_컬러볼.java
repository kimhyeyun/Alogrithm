import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_10800_컬러볼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<int[]> balls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            balls.add(new int[]{i, c, s});
        }

        balls.sort(Comparator.comparingInt(o -> o[2]));

        int[] answer = new int[N];
        int[] colors = new int[N + 1];
        int ballIndex = 0, sum = 0;

        for (int i = 0; i < N; i++) {
            int[] cur = balls.get(i);

            while (balls.get(ballIndex)[2] < cur[2]) {
                sum += balls.get(ballIndex)[2];
                colors[balls.get(ballIndex)[1]] += balls.get(ballIndex)[2];
                ballIndex += 1;
            }
            answer[cur[0]] = sum - colors[cur[1]];
        }

        for (int a : answer) {
            System.out.println(a);
        }
    }
}
