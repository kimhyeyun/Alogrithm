package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_10809_알파벳_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int[] alphabetIdx = new int[26];

        Arrays.fill(alphabetIdx, -1);
        for(int i = 0 ; i < str.length() ; i++){
            if(alphabetIdx[str.charAt(i) - 'a'] == -1)
                alphabetIdx[str.charAt(i) - 'a'] = i;
        }

        for(int i = 0 ; i < alphabetIdx.length ; i++)
            System.out.print(alphabetIdx[i] + " ");
        System.out.println();
    }
}
