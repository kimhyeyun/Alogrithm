package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int[][] stickers;
        long[][] DP;

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){
            int N = Integer.parseInt(br.readLine());
            stickers = new int[2][N+1];
            DP = new long[2][N+1];

            for(int i = 0; i < 2; i++){
                stringTokenizer = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N ; j++){
                    stickers[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            DP[0][0] = stickers[0][0];
            DP[1][0] = stickers[1][0];
            DP[0][1] = stickers[0][1] + DP[1][0];
            DP[1][1] = stickers[1][1] + DP[0][0];

            for(int i = 2; i < N ;i++){
                DP[0][i] = stickers[0][i] + Math.max(DP[1][i-1], DP[1][i-2]);
                DP[1][i] = stickers[1][i] + Math.max(DP[0][i-1], DP[0][i-2]);
            }

            long answer = Math.max(DP[0][N-1], DP[1][N-1]);
            System.out.println(answer);

        }
    }
}
