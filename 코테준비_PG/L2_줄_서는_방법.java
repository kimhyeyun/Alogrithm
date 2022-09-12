import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L2_줄_서는_방법 {
    public static int[] solution(int n, long k) {
        List<Integer> list = new ArrayList<>();
        int[] answer = new int[n];
        long factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
            list.add(i);
        }

        k -= 1;
        int idx = 0;
        while (n > 0) {
            factorial /= n;
            answer[idx++] = list.get((int) (k / factorial));
            list.remove((int) (k / factorial));
            k %= factorial;
            n -= 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(3, 5);


    }
}
