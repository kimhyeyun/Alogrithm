import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class 이모티콘_할인행사 {

    private final int[] RATES = {10, 20, 30, 40};
    private int countOfSubscribers = 0;
    private int totalOfSales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        setRates(emoticons, users, 0, new int[emoticons.length]);

        return new int[]{countOfSubscribers, totalOfSales};
    }

    private void setRates(int[] emoticons, int[][] users, int idx, int[] rates) {
        if (idx == emoticons.length) {
            getAnswer(emoticons, users, rates);
            return;
        }

        for (int i = 0; i < RATES.length; i++) {
            rates[idx] = RATES[i];
            setRates(emoticons, users, idx + 1, rates);
        }
    }

    private void getAnswer(int[] emoticons, int[][] users, int[] rates) {
        int count = 0;
        int totalPrice = 0;

        for (int[] user : users) {
            int expense = 0;
            int rate = user[0];
            int price = user[1];

            for (int i = 0; i < rates.length; i++) {
                if (rates[i] >= rate) {
                    expense += (emoticons[i] * (100 - rates[i]) / 100);
                }

                if (expense >= price) {
                    count += 1;
                    expense = 0;
                    break;
                }
            }
            totalPrice += expense;
        }

        if (count > countOfSubscribers) {
            countOfSubscribers = count;
            totalOfSales = totalPrice;
        } else if (count == countOfSubscribers) {
            totalOfSales = Math.max(totalOfSales, totalPrice);
        }
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000}), new int[]{1, 5400});
    }
}
