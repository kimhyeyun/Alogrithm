import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 균형잡힌세상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();

            if(str.equals("."))
                break;
            
            else{
                boolean flag = true;

                for(int i = 0 ;i < str.length(); i++){
                    if(str.charAt(i) == '(' || str.charAt(i) == '['){
                        stack.push(str.charAt(i));
                    }
                    else if(str.charAt(i) == ')'){
                        if(stack.isEmpty() || stack.peek() != '('){
                            flag = false;
                            break;
                        }
                        else if(stack.peek() == '('){
                            stack.pop();
                        }
                    }
                    else if(str.charAt(i) == ']'){
                        if(stack.isEmpty() || stack.peek() != '['){
                            flag = false;
                            break;
                        }
                        else if(stack.peek() == '['){
                            stack.pop();
                        }
                    }
                }
                
                if(!stack.isEmpty() || !flag)
                    sb.append("no\n");

                else if(flag)
                    sb.append("yes\n");
            }
        }

        System.out.println(sb);
    }
}
