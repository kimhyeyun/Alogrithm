package DP;

import java.util.Scanner;

public class Main_BOJ_2156_포도주_시식 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] wines = new int[N+2];
        int[] DP = new int[N+2];

        for(int i = 1 ; i <= N ; i++){
            wines[i] = scanner.nextInt();
        }

        DP[1] = wines[1];
        DP[2] = wines[1] + wines[2];

        for(int i = 3; i < N+1; i++){
            DP[i] = Math.max(DP[i-2]+wines[i], wines[i]+wines[i-1]+DP[i-3]);
            DP[i] = Math.max(DP[i], DP[i-1]);
        }
        System.out.println(DP[N]);
    }
}
