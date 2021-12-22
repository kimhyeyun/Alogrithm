package DP;

import java.util.Scanner;

public class Main_BOJ_1463_1로만들기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] DP = new int[N+1];
        DP[0] = 0;
        DP[1] = 0;

        for(int i = 2; i <= N ;i++){
            DP[i] = DP[i-1] + 1;

            if(i % 2 == 0){
                DP[i] = Math.min(DP[i], DP[i/2] + 1);
            }
            if(i % 3 == 0){
                DP[i] = Math.min(DP[i], DP[i/3] + 1);
            }
        }

        System.out.println(DP[N]);

    }
}
