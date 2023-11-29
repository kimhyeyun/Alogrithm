import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 둘만의_암호 {

    public String solution(String s, String skip, int index) {
        String answer = "";

        boolean[] isSkipped = new boolean[26];
        for (char c : skip.toCharArray()) {
            isSkipped[c - 'a'] = true;
        }

        for (char c : s.toCharArray()) {
            for (int i = 0; i < index; i++) {
                c++;
                if(c == 123) c = 'a';
                if (isSkipped[c - 'a']) i -= 1;
            }

            answer += (char) c;
        }

        return answer;
    }


    @Test
    void test() {
        assertEquals(solution("aukks", "wbqd", 5), "happy");
    }
}
