import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Stack<int[]> towers = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());

            while (!towers.isEmpty()) {
                if (towers.peek()[1] >= height) {
                    sb.append(towers.peek()[0]).append(" ");
                    break;
                }
                towers.pop();
            }

            if (towers.isEmpty()) sb.append(0).append(" ");
            towers.push(new int[]{i, height});
        }
        System.out.println(sb);
    }
}
