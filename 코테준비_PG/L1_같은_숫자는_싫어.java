import java.util.ArrayList;
import java.util.List;

public class L1_같은_숫자는_싫어 {
    public int[] solution(int []arr) {
        List<Integer> tmpList = new ArrayList<>();
        tmpList.add(arr[0]);

        for (int a : arr) {
            if (a == tmpList.get(tmpList.size() - 1)) continue;

            tmpList.add(a);
        }

        return tmpList.stream().mapToInt(Integer::intValue).toArray();
    }
}
