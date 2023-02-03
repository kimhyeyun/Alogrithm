import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_22866_탑_보기 {
    static class Building {
        int idx, height;

        public Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Building[] buildings = new Building[N + 1];
        for (int i = 1; i <= N; i++) {
            buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
        }

        int[][] near = new int[N + 1][2]; // a와 b[0] 의 b[1] (거리)
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(near[i], 100001);
        }

        int[] count = new int[N + 1];

        Stack<Building> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek().height <= buildings[i].height) {
                stack.pop();
            }
            count[i] += stack.size();

            if (!stack.isEmpty()) {
                int dist = Math.abs(stack.peek().idx - i);
                if (dist < near[i][1]) {
                    near[i][0] = stack.peek().idx;
                    near[i][1] = dist;
                } else if (dist == near[i][1] && stack.peek().idx < near[i][0]) {
                    near[i][0] = stack.peek().idx;
                }
            }
            stack.push(buildings[i]);
        }


        stack.clear();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().height <= buildings[i].height) {
                stack.pop();
            }
            count[i] += stack.size();

            if (!stack.isEmpty()) {
                int dist = Math.abs(stack.peek().idx - i);
                if (dist < near[i][1]) {
                    near[i][0] = stack.peek().idx;
                    near[i][1] = dist;
                } else if (dist == near[i][1] && stack.peek().idx < near[i][0]) {
                    near[i][0] = stack.peek().idx;
                }
            }
            stack.push(buildings[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {
                sb.append(0);
            } else {
                sb.append(count[i]).append(" ").append(near[i][0]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
