import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 괄호_변환 {
    int pos;
    public String solution(String p) {
        if(p.isEmpty()) return p;

        boolean isCorrected = isCorrected(p);

        String u = p.substring(0, pos + 1);
        String v = p.substring(pos + 1);

        if(isCorrected) return u + solution(v);

        String answer = "(" + solution(v) + ")";
        u = u.substring(1, u.length() - 1);

        for (char c : u.toCharArray()) {
            if(c == '(') answer += ")";
            else answer += "(";
        }
        return answer;
    }

    private boolean isCorrected(String p) {
        int left = 0, right = 0;
        boolean result = true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                stack.add(p.charAt(i));
                left += 1;
            } else {
                if(stack.isEmpty()) result = false;
                else stack.pop();
                right += 1;
            }

            if (left == right) {
                pos = i;
                return result;
            }
        }
        return true;
    }

    @Test
    void test() {
        assertEquals(solution(")("), "()");
        assertEquals(solution("()))((()"), "()(())()");
    }
}
