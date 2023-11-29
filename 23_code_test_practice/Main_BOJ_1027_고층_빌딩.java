import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1027_고층_빌딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        for (int i = 0; i < N - 1; i++) {
            answer[i] += 1;
            answer[i + 1] += 1;

            double slope = height[i + 1] - height[i];
            for (int j = i + 2; j < N; j++) {
                double nextSlope = (double) (height[j] - height[i]) / (double) (j - i);
                if (nextSlope > slope) {
                    slope = nextSlope;
                    answer[i] += 1;
                    answer[j] += 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, answer[i]);
        }

        System.out.println(max);
    }
}
