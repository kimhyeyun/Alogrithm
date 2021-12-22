package DP;

import java.util.Scanner;

public class Main_BOJ_11727_2xn_타일링_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] DP = new int[N+1];
        DP[0] = 1;
        DP[1] = 1;

        for(int i = 2; i <= N ; i++){
            DP[i] = (DP[i-1] + DP[i-2]*2) % 10007;
        }
        System.out.println(DP[N]);

    }
}
