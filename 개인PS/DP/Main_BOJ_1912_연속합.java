package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] DP = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        DP[0] = array[0];
        int answer = DP[0];
        for(int i = 1; i < N ;i++){
            DP[i] = Math.max(DP[i-1] + array[i], array[i]);
            answer = Math.max(answer, DP[i]);
        }

        System.out.println(answer);
    }
}
