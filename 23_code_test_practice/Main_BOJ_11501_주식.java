import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11501_주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long[] stocks = new long[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                stocks[i] = Long.parseLong(st.nextToken());
            }

            long max = 0, answer = 0;
            for (int i = N - 1; i >= 0; i--) {
                if(max < stocks[i]) max = stocks[i];
                else answer += (max - stocks[i]);
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
