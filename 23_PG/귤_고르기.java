import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 귤_고르기 {

    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> o2.getValue() - o1.getValue());

        for (Map.Entry<Integer, Integer> entry : entries) {
            if(entry.getValue() >= k) {
                answer += 1;
                break;
            }else{
                k -= entry.getValue();
                answer += 1;
            }
        }

        return answer;
    }

    @Test
    void test() {
        assertEquals(solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}), 3);
        assertEquals(solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3}), 2);
        assertEquals(solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3}), 1);
    }
}
