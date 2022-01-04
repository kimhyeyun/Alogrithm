package DP;

import java.util.Scanner;

public class Main_BOJ_10844_쉬운_계단_수 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[][] stairs = new int[101][10]; // [숫자의 길이][마지막 숫자]
        int mod = 1000000000;

        stairs[1][0] = 0;
        for(int i = 1; i < 10 ; i++){
            stairs[1][i] = 1;
        }

        for(int i = 2; i < 101; i++){
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    // 마지막 수가 0이라면 앞에 올수 있는 수는 1 뿐
                    stairs[i][j] = stairs[i-1][1] % mod;
                }
                else if(j == 9){
                    // 마지막 수가 9라면 앞에 올 수 있는 수는 8 뿐
                    stairs[i][j] = stairs[i-1][8] % mod;
                }
                else{
                    stairs[i][j] = (stairs[i-1][j-1] + stairs[i-1][j+1]) % mod;
                }
            }
        }

        long ans = 0;
        for(int i = 0 ; i < 10 ; i++){
            ans += (stairs[N][i] % mod);
        }

        System.out.println(ans % mod);
    }
}
