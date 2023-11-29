import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 크기가_작은_부분문자열 {

    public int solution(String t, String p) {
        int answer = 0;

        long standard = Long.parseLong(p);
        int len = p.length();

        for (int i = 0; i < t.length(); i++) {
            if(i + len - 1 >= t.length()) break;

            long num = Long.parseLong(t.substring(i, i + len));
            if(num <= standard) answer += 1;
        }

        return answer;
    }

    @Test
    void test() {
        assertEquals(solution("3141592", "271"), 2);
        assertEquals(solution("500220839878", "7"), 8);
        assertEquals(solution("10203", "15"), 3);
    }

}
