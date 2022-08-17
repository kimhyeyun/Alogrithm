import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L1_나누어_떨어지는_숫자_배열 {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> answer = new ArrayList<>();
        for (int a : arr) {
            if(a % divisor == 0) answer.add(a);
        }

        if(answer.size() == 0) return new int[]{-1};

        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
