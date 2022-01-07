package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11055_가장_큰_증가_부분_수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] DP = new int[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int ans = 0;
        for(int i = 0; i < N ; i++){
            DP[i] = array[i];
            for(int j = 0; j < i ; j++){
                if(array[j] < array[i] && DP[i] < DP[j] + array[i])
                    DP[i] = DP[j] + array[i];
            }

            ans = Math.max(ans, DP[i]);
        }
        System.out.println(ans);
    }
}
