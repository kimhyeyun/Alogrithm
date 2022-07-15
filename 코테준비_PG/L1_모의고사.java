import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1_모의고사 {
    public int[] solution(int[] answers) {
        List<Integer> list = new ArrayList<>();

        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int oneCnt = 0, twoCnt = 0, threeCnt = 0;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == one[i % 5]) oneCnt += 1;
            if(answers[i] == two[i % 8]) twoCnt += 1;
            if(answers[i] == three[i % 10]) threeCnt += 1;
        }

        int max = Math.max(oneCnt, Math.max(twoCnt, threeCnt));
        if(max == oneCnt) list.add(1);
        if(max == twoCnt) list.add(2);
        if(max == threeCnt) list.add(3);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
