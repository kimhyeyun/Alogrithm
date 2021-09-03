import java.util.Stack;

public class 괄호회전하기 {

    public static int solution(String s) {
        int answer = 0;

        for(int i = 0 ; i < s.length() ; i++){
            answer += isCorrect(s) ? 1 : 0;

            s = s.substring(1, s.length()) + s.substring(0, 1);
        }

        return answer;
    }

    private static boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
                stack.push(s.charAt(i));
            }
            else{
                if(!stack.isEmpty()){
                    if(s.charAt(i) == ')' && stack.peek() == '(')
                        stack.pop();
                    else if(s.charAt(i) == '}' && stack.peek() == '{')
                        stack.pop();
                    else if(s.charAt(i) == ']' && stack.peek() == '[')
                        stack.pop();
                    else
                        return false;

                }
                else{
                    return false;
                }
            }
        }

        if(stack.isEmpty())
            return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution("}]()[{"));
    }
}
