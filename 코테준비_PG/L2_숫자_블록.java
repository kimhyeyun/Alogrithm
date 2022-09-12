import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L2_숫자_블록 {
    public int[] solution(long begin, long end) {
        List<Integer> board = new ArrayList<>();
        int len = (int) (end - begin + 1);

        for (long i = begin; i <= end; i++) {
            if (i == 1) {
                board.add(0);
                continue;
            } else board.add((int) block(i));
         }

        return board.stream().mapToInt(Integer::intValue).toArray();
    }

    private long block(long n) {
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if(n / i > 10000000) continue;
                return n / i;
            }
        }
        return 1;
    }
}
