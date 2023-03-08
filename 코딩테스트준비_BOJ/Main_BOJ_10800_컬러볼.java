import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_10800_컬러볼 {
    static class Ball{
        int idx, color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Ball> balls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            balls.add(new Ball(i, c, s));
        }

        Collections.sort(balls, (o1, o2) -> o1.size - o2.size);

        int[] answer = new int[N];
        int sum = 0;
        int[] color = new int[N + 1];

        for (int i = 0, j = 0; i < N; i++) {
            Ball a = balls.get(i);
            Ball b = balls.get(j);

            while (b.size < a.size) {
                sum += b.size;
                color[b.color] += b.size;

                b = balls.get(++j);
            }
            answer[a.idx] = sum - color[a.color];
        }
        for (int a : answer) {
            sb.append(a).append("\n");
        }
        System.out.println(sb);
    }
}
