package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_10799_쇠막대기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();
        int ans = 0;

        String str = br.readLine();
        for(int i = 0 ; i < str.length() ; i++){
            if(str.charAt(i) == '(') stack.push('(');
            else{
                stack.pop();

                if(str.charAt(i-1) == '(') ans += stack.size();
                else ans += 1;
            }
        }
        System.out.println(ans);
    }
}
