import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_1863_스카이라인_쉬운거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i] = y;
        }

        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek() > arr[i]) {
                answer += 1;
                stack.pop();
            }

            if(!stack.isEmpty() && stack.peek() == arr[i]) continue;
            stack.push(arr[i]);
        }

        System.out.println(answer);
    }
}
