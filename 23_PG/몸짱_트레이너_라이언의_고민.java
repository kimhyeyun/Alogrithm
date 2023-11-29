import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 몸짱_트레이너_라이언의_고민 {

    public int solution(int n, int m, int[][] timetable) {

        int[] times = new int[721];

        for (int i = 0; i < m; i++) {
            times[timetable[i][0] - 600] += 1;
            times[timetable[i][1] - 600 + 1] -= 1;
        }

        int sum = 0;
        int max = 0;

        for (int i = 0; i <= 720; i++) {
            sum += times[i];
            times[i] = sum;
            max = Math.max(max, times[i]);
        }

        if(max <= 1) return 0;

        List<int[]> list = new ArrayList<>();
        for (int dist = 2 * (n - 1); dist > 0; dist--) {
            for (int y = 0; y < n; y++) {
                list.clear();
                int count = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if(i == 0 && j == y) continue;

                        boolean flag = true;
                        for (int[] p : list) {
                            if(Math.abs(p[0] - i) + Math.abs(p[1] - j) >= dist) continue;
                            flag = false;
                            break;
                        }

                        if (flag) {
                            if(++count == max) return dist;
                            list.add(new int[]{i, j});
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Test
    void test() {
        assertEquals(solution(3, 2, new int[][]{{1170, 1210}, {1200, 1260}}), 4);
        assertEquals(solution(4, 5, new int[][]{{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}}), 4);
    }
}
