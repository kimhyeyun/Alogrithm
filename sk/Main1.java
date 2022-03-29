import java.util.HashMap;
import java.util.Map;

public class Main1 {

    public static int solution(int money, int[] costs) {
        int answer = 0;

        int[] coins = {1, 5, 10, 50, 100, 500};
        int m = money;
        for (int i = coins.length - 1; i >= 0; i--) {
            if (m / coins[i] > 0) {

            }
        }

        return answer;
    }
    public static void main(String[] args) {

    }
}
