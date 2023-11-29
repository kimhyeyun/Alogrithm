import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BOJ_3043_장난감_탱크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Tank[] tanks = new Tank[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tanks[i] = new Tank(x, y, i + 1);
        }
        Arrays.sort(tanks, (o1, o2) -> o1.y - o2.y);

        int count = 0;
        for (int i = 0; i < N; i++) {
            Tank cur = tanks[i];
            int target = i + 1;

            while (cur.x > target) {
                count += 1;
                sb.append(cur.index).append(" ").append("U").append("\n");
                cur.x -= 1;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            Tank cur = tanks[i];
            int target = i + 1;

            while (cur.x < target) {
                count += 1;
                sb.append(cur.index).append(" ").append("D").append("\n");
                cur.x += 1;
            }
        }

        Arrays.sort(tanks, ((o1, o2) -> o1.y - o2.y));

        for (int i = 0; i < N; i++) {
            Tank cur = tanks[i];
            int target = i + 1;

            while (cur.y > target) {
                count += 1;
                sb.append(cur.index).append(" ").append("L").append("\n");
                cur.y -= 1;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            Tank cur = tanks[i];
            int target = i + 1;

            while (cur.y < target) {
                count += 1;
                sb.append(cur.index).append(" ").append("R").append("\n");
                cur.y += 1;
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    private static class Tank {
        int x, y;
        int index;

        public Tank(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
