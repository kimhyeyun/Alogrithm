package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_10808_알파벳_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int[] alphabet = new int[26];

        for(char c : str.toCharArray()){
            alphabet[c - 'a']++;
        }

        for(int i = 0 ; i < alphabet.length; i++){
            System.out.print(alphabet[i] + " ");
        }
        System.out.println();
    }
}
