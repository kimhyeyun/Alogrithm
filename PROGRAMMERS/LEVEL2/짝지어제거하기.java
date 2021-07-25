import java.util.Stack;

public class 짝지어제거하기 {
    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
    }

    public static int solution(String s)
    {
        Stack<Character> stk = new Stack<>();

        for(char c : s.toCharArray()){
            if(!stk.isEmpty() && stk.peek() == c)
                stk.pop();
            else
                stk.add(c);
        }

        return stk.isEmpty() ? 1 : 0;
    }
}
