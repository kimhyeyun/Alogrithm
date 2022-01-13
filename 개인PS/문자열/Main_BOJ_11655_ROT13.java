package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11655_ROT13 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String answer = "";

        for(char c : input.toCharArray()){
            if('a' <= c && c <= 'z'){
              c += 13;
              if('z' < c){
                  c -= 'z';
                  c += 96;
              }
            }else if('A' <= c && c <= 'Z'){
                c += 13;
                if('Z' < c){
                    c -= 'Z';
                    c += 64;
                }
            }
            answer += c;
        }

        System.out.println(answer);
    }
}
