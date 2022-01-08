package DP;

import java.util.Scanner;

public class Main_BOJ_2133_타일_채우기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] DP = new int[N+1];
        if(N%2 == 1){
            System.out.println(0);
            return;
        }

        DP[0] = 1;
        DP[2] = 3;
        for(int i = 4 ; i <= N ; i += 2){
            DP[i] = 3*DP[i-2];
            for(int j = 4;  j <= i; j += 2){
                DP[i] += (2*DP[i-j]);
            }
        }
        System.out.println(DP[N]);
    }
}
