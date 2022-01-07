package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11054_가장_긴_바이토닉_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        int[] leftDP = new int[N];
        int[] rightDP = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            leftDP[i] = 1;
            for(int j = 0 ; j < i ; j++){
                if(array[j] < array[i] && leftDP[i] <= leftDP[j])
                    leftDP[i] = leftDP[j] + 1;
            }
        }

        for(int i = N-1; i >= 0 ; i--){
            rightDP[i] = 1;
            for(int j = N-1; j > i; j--){
                if(array[j] < array[i] && rightDP[i] <= rightDP[j])
                    rightDP[i] = rightDP[j] + 1;
            }
        }

        int ans = 0;
        for(int i = 0; i < N ; i++){
            ans = Math.max(ans, leftDP[i] + rightDP[i]);
        }

        System.out.println(ans-1);
    }
}
