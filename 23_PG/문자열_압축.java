import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 문자열_압축 {

    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            String target = s.substring(0, i);
            String cur = "";
            int count = 1;

            StringBuilder sb = new StringBuilder();

            for (int start = i; start <= s.length(); start += i) {
                if(s.length() <= start + i) cur = s.substring(start);
                else cur = s.substring(start, start + i);

                if(cur.equals(target)) count += 1;
                else if (count == 1) {
                    sb.append(target);
                    target = cur;
                } else {
                    sb.append(count).append(target);
                    target = cur;
                    count = 1;
                }
            }

            if(i != target.length()) sb.append(target);
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }

    @Test
    void test() {
        assertEquals(solution("abcabcabcabcdededededede"), 14);
    }
}
