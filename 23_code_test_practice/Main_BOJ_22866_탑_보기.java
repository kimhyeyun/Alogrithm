import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_22866_탑_보기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N + 1];
        int[] near = new int[N + 1];
        int[] count = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            near[i] = Integer.MAX_VALUE;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) stack.pop();

            count[i] = stack.size();
            if(count[i] > 0) near[i] = stack.peek();

            stack.push(i);
        }

        stack = new Stack<>();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) stack.pop();

            count[i] += stack.size();
            if(!stack.isEmpty() && count[i] > 0 && Math.abs(stack.peek() - i) < Math.abs(i - near[i])) near[i] = stack.peek();

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(count[i]).append(" ");
            if(count[i] > 0) sb.append(near[i]);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
