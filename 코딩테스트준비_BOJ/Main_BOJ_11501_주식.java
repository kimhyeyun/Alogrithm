import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            long[] stocks = new long[N];
            for (int i = 0; i < N; i++) {
                stocks[i] = Integer.parseInt(st.nextToken());
            }

            long max = 0;
            long answer = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (stocks[i] > max) {
                    max = stocks[i];
                } else {
                    answer += (max - stocks[i]);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
