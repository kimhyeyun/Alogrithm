import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_12865_평범한_배낭 {
    static int N, K;
    static int[][] DP;
    static int[] W, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        W = new int[N];
        V = new int[N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(stringTokenizer.nextToken());
            V[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        DP = new int[N][K + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(DP[i], -1);
        }

        System.out.println(knapsack(N - 1, K));
    }

    private static int knapsack(int i, int k) {
        if(i < 0) return 0;
        if (DP[i][k] == -1) {
            if(W[i] > k) DP[i][k] = knapsack(i - 1, k);
            else DP[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - W[i]) + V[i]);
        }

        return DP[i][k];
    }


}
