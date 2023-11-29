import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 압축 {

    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> dictionary = new HashMap<>();
        int idx = 1;

        for (char a = 'A'; a <= 'Z'; a++) {
            dictionary.put(a + "", idx++);
        }

        for (int i = 0; i < msg.length(); i++) {
            int num = 1;

            while (i + num <= msg.length() && dictionary.containsKey(msg.substring(i, i + num))) {
                num += 1;
            }

            if (i + num > msg.length()) {
                list.add(dictionary.get(msg.substring(i)));
                break;
            }

            list.add(dictionary.get(msg.substring(i, i + num - 1)));
            dictionary.put(msg.substring(i, i + num), idx++);
            i += num - 2;
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void test() {
        assertArrayEquals(solution("TOBEORNOTTOBEORTOBEORNOT"), new int[]{20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34});

    }
}
