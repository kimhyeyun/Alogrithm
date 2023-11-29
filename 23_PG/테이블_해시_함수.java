import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 테이블_해시_함수 {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            return o1[col - 1] - o2[col - 1];
        });

        List<Integer> ans = new ArrayList<>();
        for (int i = row_begin - 1; i < row_end; i++) {
            int[] tuple = data[i];

            int sum = 0;
            for (int j = 0; j < tuple.length; j++) {
                sum += (tuple[j] % (i + 1));
            }
            ans.add(sum);
        }

        answer = ans.get(0);
        for (int i = 1; i < ans.size(); i++) {
            answer ^= ans.get(i);
        }

        return answer;
    }


    @Test
    void test() {
        assertEquals(solution(new int[][]{{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}}, 2, 2, 3), 4);
    }
}
