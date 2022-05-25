import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_6549_히스토그램에서_가장_큰_직사각형 {

    static int[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder sb = new StringBuilder();

        int N;
        while (true) {
            stringTokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());

            if(N == 0) break;

            histogram = new int[N];
            for (int i = 0; i < N; i++) {
                histogram[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            sb.append(getArea(N)).append("\n");
        }
        System.out.println(sb);
    }

    private static long getArea(int len) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                int h = histogram[stack.pop()];
                long w = stack.isEmpty() ? i : i - 1 - stack.peek();

                maxArea = Math.max(maxArea, h * w);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int h = histogram[stack.pop()];
            long w = stack.isEmpty() ? len : len - 1 - stack.peek();

            maxArea = Math.max(maxArea, w * h);
        }

        return maxArea;
    }
}
