package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11053_가장_긴_증가하는_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] DP = new int[N];
        stringTokenizer = new StringTokenizer(br.readLine());

        for(int i = 0; i < N ; i++){
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int ans = 0;
        for(int i = 0 ; i < N ; i++){
            DP[i] = 1;
            for(int j = 0 ; j < i; j++){
                if(array[j] < array[i]){
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
            ans = Math.max(ans, DP[i]);
        }
        System.out.println(ans);
    }
}
