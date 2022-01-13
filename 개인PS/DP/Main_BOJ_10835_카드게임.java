package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_10835_카드게임 {
    static int N;
    static int[] leftCard, rightCard;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        leftCard = new int[N];
        rightCard = new int[N];
        DP = new int[N][N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            leftCard[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            rightCard[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int[] arr : DP){
            Arrays.fill(arr, -1);
        }

        System.out.println(game(0, 0));
    }

    private static int game(int l, int r) {
        if(l >= N || r >= N)
            return 0;
        if(DP[l][r] != -1)
            return DP[l][r];

        DP[l][r] = 0;

        if(rightCard[r] < leftCard[l])
            DP[l][r] = game(l, r + 1) + rightCard[r];
        else{
            int discardLeft = game(l + 1, r);
            int discardBoth = game(l + 1, r + 1);
            DP[l][r] = Math.max(discardLeft, discardBoth);
        }

        return DP[l][r];
    }
}
