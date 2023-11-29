import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class 대충_만든_자판 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                int index = j + 1;
                if (map.containsKey(keymap[i].charAt(j)) && index < map.get(keymap[i].charAt(j))) {
                    map.put(keymap[i].charAt(j), index);
                    continue;
                }

                if (!map.containsKey(keymap[i].charAt(j))) {
                    map.put(keymap[i].charAt(j), index);
                }
            }
        }

        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int sum = 0;

            for (char t : target.toCharArray()) {
                if (!map.containsKey(t)) {
                    answer[i] = -1;
                    break;
                }

                sum += map.get(t);
            }
            if(answer[i] != -1) answer[i] = sum;
        }

        return answer;
    }

    @Test
    void test() {
        assertArrayEquals(solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"}), new int[]{9, 4});
        assertArrayEquals(solution(new String[]{"AA"}, new String[]{"B"}), new int[]{-1});
        assertArrayEquals(solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA", "BGZ"}), new int[]{4, 6});
    }
}
