import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 마법의_엘리베이터 {

    public int solution(int storey) {
        int answer = 0;

        char[] chars = String.valueOf(storey).toCharArray();
        int[] ints = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            ints[i] = chars[i] - '0';
        }

        for (int i = ints.length - 1; i >= 0; i--) {
            int anInt = ints[i];

            if (anInt >= 6) {
                answer += (10 - anInt);
                if (i - 1 < 0) {
                    answer += 1;
                    continue;
                }
                ints[i - 1] += 1;
            } else if (anInt <= 4) {
                answer += anInt;
            } else if (anInt == 5) {
                if (i - 1 < 0) {
                    answer += 5;
                    continue;
                } else {
                    if (ints[i - 1] < 5) {
                        answer += anInt;
                    } else {
                        answer += 5;
                        ints[i - 1] += 1;
                    }
                }
            }
        }
        return answer;
    }

    @Test
    void test() {
        assertEquals(solution(16), 6);
        assertEquals(solution(2554), 16);
    }
}
