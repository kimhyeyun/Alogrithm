import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class L3_숫자_타자_대회 {
    class Fingers {
        int left;
        int right;

        public Fingers(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fingers fingers = (Fingers) o;
            return left == fingers.left && right == fingers.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }
    int[][] weight = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    Map<Fingers, Integer> weightsAndFingerPos, curWeightsAndFingerPos;



    public int solution(String numbers) {
        int answer = 0;

        weightsAndFingerPos = new HashMap<>();
        weightsAndFingerPos.put(new Fingers(4, 6), 0);

        for (int i = 0; i < numbers.length(); i++) {
            int number = numbers.charAt(i) - '0';

            curWeightsAndFingerPos = new HashMap<>();
            pressNumberButton(number);

            weightsAndFingerPos = curWeightsAndFingerPos;
        }

        return findMinTotalWeight();
    }

    private int findMinTotalWeight() {
        int min = Integer.MAX_VALUE;

        for (Fingers fingers : weightsAndFingerPos.keySet()) {
            int total = weightsAndFingerPos.get(fingers);

            min = Math.min(total, min);
        }
        return min;
    }

    private void pressNumberButton(int number) {
        for (Fingers fingers : weightsAndFingerPos.keySet()) {
            int left = fingers.left;
            int right = fingers.right;
            int totalWeight = weightsAndFingerPos.get(fingers);

            Fingers next;

            if (number == left) {
                next = new Fingers(number, right);
                if (canMove(next, totalWeight + 1)) {
                    curWeightsAndFingerPos.put(next, totalWeight + 1);
                }
            } else if (number == right) {
                next = new Fingers(left, number);
                if (canMove(next, totalWeight + 1)) {
                    curWeightsAndFingerPos.put(next, totalWeight + 1);
                }
            }else{
                next = new Fingers(number, right);
                if (canMove(next, totalWeight + weight[left][number])) {
                    curWeightsAndFingerPos.put(next, totalWeight + weight[left][number]);
                }

                next = new Fingers(left, number);
                if (canMove(next, totalWeight + weight[right][number])) {
                    curWeightsAndFingerPos.put(next, totalWeight + weight[right][number]);
                }
            }
        }
    }

    private boolean canMove(Fingers fingers, int weight) {
        return (!curWeightsAndFingerPos.containsKey(fingers) || curWeightsAndFingerPos.get(fingers) > weight);
    }



    @Test
    void test() {
        assertEquals(10, solution("1756"));
        assertEquals(8, solution("5123"));

        solution("151506");
    }
}
