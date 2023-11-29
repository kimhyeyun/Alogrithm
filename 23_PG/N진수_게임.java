import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N진수_게임 {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int count = 1;

        int num = 0;
        String toN = "";

        while (toN.length() < t * m) {
            toN += Integer.toString(num, n);
            num += 1;
        }

        for (int i = 0; i < t; i++) {
            answer += toN.charAt(p - 1);
            p += m;
        }

        return answer.toUpperCase();
    }

    @Test
    void test() {
        assertEquals(solution(16, 16, 2, 1), "02468ACE11111111");
        assertEquals(solution(2, 4, 2, 1), "0111");
        assertEquals(solution(16, 16, 2, 2), "13579BDF01234567");
    }
}
