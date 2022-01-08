package DP;

import java.util.Scanner;

public class Main_BOJ_9461_파도반_수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] DP = new long[101];
        DP[1] = 1;
        DP[2] = 1;

        for(int i = 3; i < 101 ;i++){
            DP[i] = DP[i-2] + DP[i-3];
        }

        int testCase = scanner.nextInt();
        while(testCase-- > 0){
            int N = scanner.nextInt();

            System.out.println(DP[N]);
        }
    }
}
