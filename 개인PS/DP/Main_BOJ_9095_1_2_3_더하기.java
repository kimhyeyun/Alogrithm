package DP;

import java.util.Scanner;

public class Main_BOJ_9095_1_2_3_더하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] DP = new int[11];

        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for(int i = 4; i < 11; i++){
            DP[i] = DP[i-1] + DP[i-2] + DP[i-3];
        }

        int testCase = sc.nextInt();

        while(testCase-- > 0){
            int N = sc.nextInt();

            System.out.println(DP[N]);

        }
    }
}
