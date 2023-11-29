import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 뒤에_있는_큰_수_찾기 {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];

            while (!q.isEmpty() && q.peek()[1] < n){
                answer[q.poll()[0]] = n;
            }

            q.add(new int[]{i, n});
        }

        while (!q.isEmpty()) {
            answer[q.poll()[0]] = -1;
        }

        return answer;
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution(new int[]{2, 3, 3, 5}), new int[]{3, 5, 5, -1});
        Assertions.assertArrayEquals(solution(new int[]{9, 1, 5, 3, 6, 2}), new int[]{-1, 5, 6, 6, -1, -1});
    }
}
