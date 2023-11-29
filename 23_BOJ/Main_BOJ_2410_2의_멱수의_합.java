import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2410_2의_멱수의_합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            if(i % 2 == 0) dp[i] = (dp[i - 1] + dp[i / 2]) % 1_000_000_000;
            else dp[i] = dp[i - 1];
        }

        System.out.println(dp[N] % 1_000_000_000);

    }
}
