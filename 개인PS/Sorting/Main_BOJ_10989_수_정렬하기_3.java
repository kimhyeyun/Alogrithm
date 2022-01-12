package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_10989_수_정렬하기_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(bufferedReader.readLine());
        int[] nums = new int[10001];

        while(N-- > 0){
            int x = Integer.parseInt(bufferedReader.readLine());
            nums[x]++;
        }

        for(int i = 0 ; i < 10001; i++){
            while(nums[i] > 0){
                stringBuilder.append(i).append("\n");
                nums[i]--;
            }
        }
        System.out.println(stringBuilder);
    }
}
