import java.util.Stack;

public class L2_올바른_괄호 {
    public static boolean solution(String s){
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                else{
                    stack.pop();
                }
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(")()("));
    }
}
