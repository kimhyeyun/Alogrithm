import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String expr = br.readLine();

        for(int i = 0;i < expr.length();i++){
            if('A' <= expr.charAt(i) && expr.charAt(i) <= 'Z'){
                sb.append(expr.charAt(i));
            }

            switch(expr.charAt(i)){
                // 연산자라면 자기보다 낮은 우선순위의 연산자가 나올때까지 pop 하고 push
                case '*':
                case '/':
                    while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')){
                        sb.append(stack.pop());
                    }
                    stack.push(expr.charAt(i));
                    break;

                case '+':
                case '-':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.push(expr.charAt(i));
                    break;
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '(')
                        sb.append(stack.pop());
                    
                    stack.pop();
                    break;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
    
}
