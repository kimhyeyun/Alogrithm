package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2225_합분해 {
    static final int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        long[][] DP = new long[K+1][N+1];
        for(int i = 0 ; i < N+1; i++){
            DP[1][i] = 1;
        }

        for(int i = 2 ; i < K+1; i++){
            for(int j = 0 ; j < N+1; j++){
                for(int k = 0 ; k <= j ; k++){
                    DP[i][j] += DP[i-1][k];
                }
                DP[i][j] %= mod;
            }
        }

        System.out.println(DP[K][N]);
    }
}
