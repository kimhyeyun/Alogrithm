package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_2257_화학식량 {
    static final int H = 1;
    static final int C = 12;
    static final int O = 16;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        solution();

        System.out.println(stack.peek());
    }

    private static void solution() throws IOException {
        stack = new Stack<>();

        String formula = br.readLine();

        int before = 0;
        stack.push(0);

        for(char c : formula.toCharArray()){
            if(c == '('){
                stack.push(0);
            }
            else if('2' <= c && c <= '9'){
                stack.push(stack.pop() + (c - '0' - 1) * before);
            }
            else if(c == ')'){
                before = stack.pop();
                stack.push(stack.pop() + before);
            }
            else{
                if(c == 'H')
                    before = 1;
                else if(c == 'C')
                    before = 12;
                else
                    before = 16;

                stack.push(stack.pop() + before);
            }
        }
    }

}
