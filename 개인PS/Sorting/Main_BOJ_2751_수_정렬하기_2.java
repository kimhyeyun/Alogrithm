package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main_BOJ_2751_수_정렬하기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for(int i = 0 ; i < N ;i++){
            array[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        Arrays.sort(array);
        for(int a : array)
            sb.append(a).append("\n");

        System.out.println(sb);
    }
}
