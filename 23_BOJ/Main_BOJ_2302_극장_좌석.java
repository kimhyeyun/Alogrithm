import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2302_극장_좌석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;
        int beforeSeat = 0;
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(br.readLine());
            answer *= dp[v - beforeSeat - 1];
            beforeSeat = v;
        }

        answer *= dp[N - beforeSeat];

        System.out.println(answer);
    }
}
