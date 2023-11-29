import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 덧칠하기 {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int max = 0;

        for (int i = 0; i < section.length; i++) {
            if (section[i] < max) {
                continue;
            }

            answer += 1;
            max = section[i] + m;
        }

        return answer;
    }

    @Test
    void test() {
        assertEquals(solution(8, 4, new int[]{2, 3, 6}), 2);
    }
}
