package DP;

import java.util.Scanner;

public class Main_BOJ_11057_오르막_수 {
    static final int mod = 10007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] ascent = new int[1001][10];
        int N = scanner.nextInt();

        for (int i = 0; i < 10; i++) {
            ascent[1][i] = 1;
        }

        for (int i = 2; i < 1001; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    ascent[i][j] += (ascent[i - 1][k] % mod);
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += (ascent[N][i] % mod);
        }
        System.out.println(ans % mod);

    }
}
