import java.util.Stack;

public class L2_괄호_변환 {
    static int pos;
    public static String solution(String p) {

        String answer = "";

        if(p.isEmpty()) return p;

        boolean correctOfU = isCorrect(p);

        String u = p.substring(0, pos + 1);
        String v = p.substring(pos + 1);

        if(correctOfU) return u + solution(v);

        answer = "(" + solution(v) + ")";
        u = u.substring(1, u.length() - 1);

        for (char c : u.toCharArray()) {
            if(c == '(') answer += ")";
            else answer += "(";
        }

        return answer;

    }

    private static boolean isCorrect(String p) {
        int left = 0, right = 0;
        boolean result = true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                left += 1;
                stack.push('(');
            } else {
                right += 1;
                if(stack.isEmpty()) result = false;
                else stack.pop();
            }
            if(left == right){
                pos = i;
                return result;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }
}
