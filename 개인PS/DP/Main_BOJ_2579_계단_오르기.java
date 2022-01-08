package DP;

import java.util.Scanner;

public class Main_BOJ_2579_계단_오르기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] stairs = new int[N+2];
        int[] DP = new int[N+2];

        for(int i = 1; i <= N ; i++){
            stairs[i] = scanner.nextInt();
        }

        DP[1] = stairs[1];
        DP[2] = DP[1] + stairs[2];
        for(int i = 3; i <= N ;i++){
            DP[i] = Math.max(stairs[i]+DP[i-2], stairs[i] + stairs[i-1] + DP[i-3]);
        }

        System.out.println(DP[N]);
    }
}
