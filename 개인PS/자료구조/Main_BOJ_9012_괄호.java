package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack;
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0){
            stack = new Stack<>();
            String str = br.readLine();
            boolean flag = false;

            for(char c : str.toCharArray()){
                if(c == '(') stack.push(c);
                else{
                    if(stack.isEmpty()){
                        flag = true;
                        break;
                    } else stack.pop();
                }
            }
            if(!stack.isEmpty() || flag)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }
}
