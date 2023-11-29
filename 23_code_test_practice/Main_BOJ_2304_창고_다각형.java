import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_2304_창고_다각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[1001];

        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            start = Math.min(start, L);
            end = Math.max(end, L);

            heights[L] = H;
        }

        Stack<Integer> stack = new Stack<>();

        int tmp = heights[start];
        for (int i = start; i <= end; i++) {
            if(heights[i] < tmp) stack.push(i);
            else {
                while (!stack.isEmpty()) {
                    int x = stack.pop();
                    heights[x] = tmp;
                }
                tmp = heights[i];
            }
        }

        stack.clear();

        tmp = heights[end];
        for (int i = end; i >= start; i--) {
            if(heights[i] < tmp) stack.push(i);
            else {
                while (!stack.isEmpty()) {
                    int x = stack.pop();
                    heights[x] = tmp;
                }
                tmp = heights[i];
            }
        }

        int answer = 0;
        for (int i = start; i <= end; i++) {
            answer += heights[i];
        }

        System.out.println(answer);
    }
}
