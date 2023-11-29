import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 도둑질 {
    public int solution(int[] money) {
        int[] dp1 = new int[money.length];
        int[] dp2 = new int[money.length];

        dp1[0] = money[0];
        dp1[1] = money[0];

        for (int i = 2; i < money.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
        }

        dp2[0] = 0;
        dp2[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }

        return Math.max(dp1[money.length - 2], dp2[money.length - 1]);
    }

    @Test
    void test() {
        assertEquals(solution(new int[]{1, 2, 3, 1}), 4);
        assertEquals(solution(new int[]{10, 5, 3, 1, 10}), 15);
    }
}
