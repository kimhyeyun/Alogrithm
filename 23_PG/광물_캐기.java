import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 광물_캐기 {

    int[][] fatigues = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    List<Integer> pickedOrder = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    int total = 0;
    int answer = Integer.MAX_VALUE;

    public int solution(int[] picks, String[] minerals) {
        total = picks[0] + picks[1] + picks[2];

        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);

        DFS(picks, minerals);

        return answer;
    }

    private void DFS(int[] picks, String[] minerals) {
        if (pickedOrder.size() == total) {
            int sum = 0;
            for (int i = 0; i < minerals.length; i++) {
                if((i/5) == pickedOrder.size()) break;
                sum += fatigues[pickedOrder.get(i / 5)][map.get(minerals[i])];
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(picks[i] == 0) continue;

            pickedOrder.add(i);
            picks[i] -= 1;
            DFS(picks, minerals);
            pickedOrder.remove(pickedOrder.size() - 1);
            picks[i] += 1;
        }
    }


    @Test
    void test() {
        assertEquals(solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}), 12);
    }
}
