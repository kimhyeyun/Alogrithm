import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 튜플 {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        s = s.replace("},{", "-");

        String[] str = s.split("-");

        Arrays.sort(str, Comparator.comparingInt(String::length));

        List<Integer> answer = new ArrayList<>();

        for (String a : str) {
            String[] t = a.split(",");

            for (int i = 0; i < t.length; i++) {
                int num = Integer.parseInt(t[i]);

                if(!answer.contains(num)) answer.add(num);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void test() {
        assertArrayEquals(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"), new int[]{2, 1, 3, 4});
        assertArrayEquals(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"), new int[]{2, 1, 3, 4});
        assertArrayEquals(solution("{{20,111},{111}}"), new int[]{111, 20});
        assertArrayEquals(solution("{{123}}"), new int[]{123});
        assertArrayEquals(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"), new int[]{3, 2, 4, 1});

    }
}
