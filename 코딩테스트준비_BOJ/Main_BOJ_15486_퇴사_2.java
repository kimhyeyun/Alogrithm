import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_15486_퇴사_2 {
    static class consult {
        int period;
        int price;

        public consult(int period, int price) {
            this.period = period;
            this.price = price;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        consult[] consults = new consult[N + 2];

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            consults[i] = new consult(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        consults[N + 1] = new consult(0, 0);


        int[] dp = new int[N + 2];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N + 1; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
            int nextDay = i + consults[i].period;
            if (nextDay < N + 2) {
                dp[nextDay] = Math.max(dp[nextDay], max + consults[i].price);
            }
        }
        System.out.println(max);
    }

}
