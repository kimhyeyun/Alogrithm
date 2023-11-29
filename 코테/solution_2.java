import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class solution_2  {
    public int solution(int[][] flowers) {
        int answer = 0;
        int end = 0;

        for (int[] flower : flowers) {
            if(end < flower[0] && end < flower[1]) {
                answer += flower[1] - flower[0];
            } else if (end >= flower[0] && end < flower[1]) {
                answer += (flower[1] - end - 1);
            } else if (end >= flower[0] && end >= flower[1]) {
                continue;
            }

            end = flower[1] - 1;
        }
        return answer;
    }

    public int solution(String[] id_list, int k) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();

        for (String id : id_list) {
            String[] ids = id.split(" ");

            Arrays.stream(ids).distinct().forEach(s -> map.put(s, map.getOrDefault(s, 0) + 1));
        }

        for (int v : map.values()) {
            if (v > k) {
                answer += k;
            } else {
                answer += v;
            }
        }
        return answer;
    }

    public int[] solution(String s, String[] times) {
        int dayCount = 1;
        boolean isContinue = true;

        String[] start = s.split(":");
        int[] startDay = new int[6];
        int[] lastDay = new int[6];

        for (int i = 0; i < start.length; i++) {
            startDay[i] = Integer.parseInt(start[i]);
            lastDay[i] = Integer.parseInt(start[i]);
        }

        for (String time : times) {
            String[] t = time.split(":");

            int[] tmp = new int[6];
            System.arraycopy(lastDay, 0, tmp, 0, 6);

            for (int i = t.length - 1; i >= 0; i--) {
                tmp[i + 2] += Integer.parseInt(t[i]);
            }

            if (tmp[5] > 59) {
                tmp[4] += tmp[5] / 60;
                tmp[5] %= 60;
            }
            if (tmp[4] > 59) {
                tmp[3] += tmp[4] / 60;
                tmp[4] %= 60;
            }
            if (tmp[3] > 23) {
                tmp[2] += tmp[3] / 24;
                tmp[3] %= 24;
            }
            if (tmp[2] > 30) {
                tmp[1] += tmp[2] / 30;
                tmp[2] %= 30;
            }
            if (tmp[1] > 12) {
                tmp[0] += tmp[1] / 12;
                tmp[1] %= 12;
            }

            if (tmp[2] == lastDay[2] + 1 && tmp[1] == lastDay[1] && tmp[0] == lastDay[0]) {
                lastDay = tmp;
                continue;
            } else if (tmp[2] == 1 && tmp[1] == 1) {
                if (lastDay[2] == 30 && lastDay[1] == 12 && tmp[0] == lastDay[0] + 1) {
                    lastDay = tmp;
                    continue;
                } else {
                    isContinue = false;
                }
            } else if (tmp[2] == lastDay[2] && tmp[1] == lastDay[1] && tmp[0] == lastDay[0]) {
                lastDay = tmp;
                continue;
            } else {
                isContinue = false;
            }
            lastDay = tmp;
        }

        if (lastDay[0] == startDay[0] && lastDay[1] == startDay[1]) {
            dayCount = lastDay[2] - startDay[2] + 1;
        } else if (lastDay[0] == startDay[0] && lastDay[1] != startDay[1]) {
            dayCount = (lastDay[1] - startDay[1] - 1) * 30;
            dayCount += (lastDay[2]);
            dayCount += (30 - startDay[2] + 1);
        } else {
            dayCount = (12 - startDay[1]) * 30 + (30 - startDay[2] + 1);
            dayCount += (lastDay[1] - 1) * 30 + lastDay[2];
        }

        return new int[]{isContinue ? 1 : 0, dayCount};
    }

    @Test
    void test() {
        assertEquals(6, solution(new int[][]{{2, 5}, {3, 7}, {10, 11}}));
        assertEquals(5, solution(new int[][]{{3, 4}, {4, 5}, {6, 7}, {8, 10}}));

        assertEquals(8, solution(new String[]{"JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY"}, 3));

        assertArrayEquals(new int[]{0, 4}, solution("2021:04:12:16:08:35", new String[]{"01:06:30:00", "01:04:12:00"}));
        assertArrayEquals(new int[]{1, 2}, solution("2021:04:12:16:08:35", new String[]{"01:06:30:00", "00:01:12:00"}));
        assertArrayEquals(new int[]{1, 2}, solution("2021:04:12:16:10:42", new String[]{"01:06:30:00"}));
        assertArrayEquals(new int[]{1, 4}, solution("2021:04:12:16:08:35", new String[]{"01:06:30:00", "01:01:12:00", "00:00:09:25"}));
    }
}

