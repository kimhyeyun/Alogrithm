package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11052_카드_구매하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        int[] cards = new int[N+1];
        for(int i = 1; i < N+1; i++){
            cards[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int[] DP = new int[N+1];
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j <= i; j++){
                DP[i] = Math.max(DP[i], DP[i-j] + cards[j]);
            }
        }

        System.out.println(DP[N]);

    }
}
