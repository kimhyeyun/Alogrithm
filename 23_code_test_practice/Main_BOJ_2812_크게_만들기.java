import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_2812_크게_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int len = N - K;

        Stack<Character> stack = new Stack<>();
        String str = br.readLine();

        for (char c : str.toCharArray()) {
            if (!stack.isEmpty()) {
                while (!stack.empty() && K > 0 && stack.peek() < c) {
                    stack.pop();
                    K -= 1;
                }
            }
            stack.push(c);
        }

        while (true) {
            if (stack.size() == len) break;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse());
    }
}
