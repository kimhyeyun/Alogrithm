import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L2_nxn_배열_자르기 {
    public static int[] solution(int n, long left, long right) {

        List<Integer> answer = new ArrayList<>();

        while (left <= right) {
            answer.add(Math.max((int) left / n + 1, (int) left % n + 1));
            left += 1;
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] answer = solution(4, 7, 14);
        for (int a : answer) {
            System.out.println(a);
        }
    }
}
