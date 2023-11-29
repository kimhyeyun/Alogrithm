import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 무인도_여행 {

    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        boolean[][] isVisited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if(maps[i].charAt(j) == 'X') continue;
                if(isVisited[i][j]) continue;

                isVisited[i][j] = true;
                int days = BFS(i, j, maps, isVisited, (int) maps[i].charAt(j) - '0');

                answer.add(days);
            }
        }

        if(answer.size() == 0) return new int[]{-1};

        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int BFS(int x, int y, String[] maps, boolean[][] isVisited, int sum) {
        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        int n = maps.length;
        int m = maps[0].length();
        int s = sum;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || n <= nx || m <= ny) continue;
                if(maps[nx].charAt(ny) == 'X' || isVisited[nx][ny]) continue;

                s += (int) maps[nx].charAt(ny) - '0';
                isVisited[nx][ny] = true;

                q.add(new int[]{nx, ny});
            }
        }

        return s;
    }

    @Test
    void test() {
        assertArrayEquals(solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"}), new int[]{1, 1, 27});
        assertArrayEquals(solution(new String[]{"XXX", "XXX", "XXX"}), new int[]{-1});
    }
}
