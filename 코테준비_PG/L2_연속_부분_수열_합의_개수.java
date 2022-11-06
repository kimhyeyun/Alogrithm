import java.util.HashSet;
import java.util.Set;

public class L2_연속_부분_수열_합의_개수 {
    public static int solution(int[] elements) {
        Set<Integer> sum = new HashSet<>();

        int tmp = 0;
        for (int i = 0; i < elements.length; i++) {
            tmp += elements[i];
        }
        sum.add(tmp);

        int size = elements.length;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < elements.length; j++) {
                tmp = 0;
                int cnt = 0;
                int idx = j;
                while (cnt < i) {
                    tmp += elements[idx];
                    idx += 1;
                    if(idx == size) idx = 0;
                    cnt += 1;
                }
                sum.add(tmp);
            }
        }

        return sum.size();
    }

    public static void main(String[] args) {
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(solution(elements));

    }
}
