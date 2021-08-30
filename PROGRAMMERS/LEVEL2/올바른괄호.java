import java.util.LinkedList;
import java.util.Stack;

public class 올바른괄호 {

    public static boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push('(');
            }
            else{
                if(stack.isEmpty()){
                    answer = false;
                    break;
                }
                else{
                    stack.pop();
                }
            }
        }

        if(!stack.isEmpty())
            answer = false;

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("(()("));
    }
}
