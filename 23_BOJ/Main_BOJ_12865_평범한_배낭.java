import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_12865_평범한_배낭 {
    static int[][] dp;
    static int[] W, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][K + 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);


        System.out.println(knapsack(N - 1, K));

    }

    private static int knapsack(int index, int weight) {
        if (index < 0) return 0;
        if (dp[index][weight] == -1) {
            if (W[index] > weight) {
                dp[index][weight] = knapsack(index - 1, weight);
            } else {
                dp[index][weight] = Math.max(knapsack(index - 1, weight), knapsack(index - 1, weight - W[index]) + V[index]);

            }
        }

        return dp[index][weight];
    }
}
