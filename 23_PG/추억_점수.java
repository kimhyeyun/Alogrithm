import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 추억_점수 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            scores.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            int sum = 0;

            for (int j = 0; j < photo[i].length; j++) {
                if (scores.containsKey(photo[i][j])) {
                    sum += scores.get(photo[i][j]);
                }
            }

            answer[i] = sum;
        }

        return answer;
    }

    @Test
    void test() {
        assertArrayEquals(solution(new String[]{"may", "kein", "kain", "radi"},
                new int[]{5, 10, 1, 3}, new String[][]{{"may", "kein", "kain", "radi"}, {"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}}),
                new int[]{19, 15, 6});
    }
}
