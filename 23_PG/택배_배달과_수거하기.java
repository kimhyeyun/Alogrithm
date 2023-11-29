import java.util.Map;
import java.util.Stack;

public class 택배_배달과_수거하기 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> pickup = new Stack<>();
        Stack<Integer> delivery = new Stack<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                delivery.push(i + 1);
            }
            for (int j = 0; j < pickups[i]; j++) {
                pickup.push(i + 1);
            }
        }

        while (!delivery.isEmpty() && !pickup.isEmpty()) {
            int lastD = delivery.peek();
            int lastP = pickup.peek();

            for (int i = 0; i < cap; i++) {
                if(!delivery.isEmpty()) delivery.pop();
                if(!pickup.isEmpty()) pickup.pop();
            }

            answer += Math.max(lastD, lastP) * 2L;
        }

        while (!delivery.isEmpty()) {
            int last = delivery.peek();

            for (int i = 0; i < cap; i++) {
                if(!delivery.isEmpty()) delivery.pop();
            }

            answer += last * 2L;
        }

        while (!pickup.isEmpty()) {
            int last = pickup.peek();

            for (int i = 0; i < cap; i++) {
                if(!pickup.isEmpty()) pickup.pop();
            }

            answer += last * 2L;
        }

        return answer;
    }

}
