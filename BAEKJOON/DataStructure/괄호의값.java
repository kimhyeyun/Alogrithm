import java.util.Scanner;
import java.util.Stack;

public class 괄호의값 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        Stack<Character> stack = new Stack<>();
        long result = 0;
        long temp = 1;
        boolean flag = false;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                temp *= 2;
                stack.push('(');
            }
            else if(str.charAt(i) == '['){
                temp *= 3;
                stack.push('[');
            }

            else if(str.charAt(i) == ')'){
                if(stack.isEmpty() || stack.peek() != '(' ){
                    flag = true;
                    break;
                }

                if(str.charAt(i-1) == '('){
                    result += temp;
                }

                stack.pop();
                temp /= 2;
            }

            else if(str.charAt(i) == ']'){
                if(stack.isEmpty() || stack.peek() != '['){
                    flag = true;
                    break;
                }

                if(str.charAt(i-1) == '[')
                    result += temp;
                
                stack.pop();
                temp /= 3;
            }
        }

        if(flag || !stack.isEmpty())
            System.out.println(0);
        else
            System.out.println(result);
    }
}
