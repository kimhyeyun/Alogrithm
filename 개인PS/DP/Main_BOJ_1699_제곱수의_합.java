package DP;

import java.util.Scanner;

public class Main_BOJ_1699_제곱수의_합 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] DP = new int[N+1];

        for(int i = 1; i <= N ; i++){
            DP[i] = i;

            for(int j = 2; j*j <= i; j++){
                DP[i] = Math.min(DP[i], DP[i - j*j] + 1);
            }
        }

        System.out.println(DP[N]);
    }
}
