import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class 에어컨 {

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        Map<Integer, Integer> tempCostMap = new HashMap<>();
        tempCostMap.put(temperature, 0);

        for (int o : onboard) {
            Map<Integer, Integer> prev = tempCostMap;
            tempCostMap = new HashMap<>();

            for (int prevTemp : prev.keySet()) {
                int prevCost = prev.get(prevTemp);
                for (int change = -1; change <= 1; change++) {
                    int temp = prevTemp + change;

                    if (o == 0 || t1 <= temp && temp <= t2) {
                        int cost = prevCost;

                        if (!(temperature == temp || (temperature - temp) * change > 0)) {
                            cost = prevCost + (change == 0 ? b : a);
                        }
                        tempCostMap.merge(temp, cost, (x, y) -> Math.min(x, y));
                    }
                }
            }
        }

        return tempCostMap.values().stream().mapToInt(Integer::intValue).min().orElse(0);
    }


    @Test
    void test() {
        solution(28, 18, 26, 10, 8, new int[]{0, 0, 1, 1, 1, 1, 1});
    }
}