import java.util.Stack;

public class L2_짝지어_제거하기 {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if(stack.isEmpty()) stack.push(c);
            else{
                if(c == stack.peek()) stack.pop();
                else stack.push(c);
            }
        }

        if(stack.isEmpty()) return 1;
        else return 0;
    }
}
