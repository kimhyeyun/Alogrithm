import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 지형_편집 {
    public long solution(int[][] land, int P, int Q) {
        long answer = 0;

        long start = land[0][0];
        long end = land[0][0];

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if(land[i][j] > end) end = land[i][j];
                if(land[i][j] < start) start = land[i][j];
            }
        }

        long mid;
        while (start <= end) {
            mid = (start + end) / 2;

            long leftCost = calculateCost(land, mid, P, Q);
            long rightCost = calculateCost(land, mid + 1, P, Q);

            if (leftCost > rightCost) {
                answer = rightCost;
                start = mid + 1;
            } else {
                answer = leftCost;
                end = mid - 1;
            }
        }
        return answer;
    }

    private long calculateCost(int[][] land, long height, int P, int Q) {
        long cost = 0;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if(land[i][j] > height) cost += (land[i][j] - height) * Q;
                else cost += (height - land[i][j]) * P;
            }
        }
        return cost;
    }

    @Test
    void test() {
        assertEquals(solution(new int[][]{{1, 2}, {2, 3}}, 3, 2), 5);
    }
}
