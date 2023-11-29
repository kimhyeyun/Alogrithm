import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class 카카오_프렌즈_컬러링북 {

    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] isVisited = new boolean[m][n];

        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(picture[i][j] == 0 || isVisited[i][j]) continue;

                isVisited[i][j] = true;
                count += 1;
                max = Math.max(BFS(i, j, m, n, picture[i][j], isVisited, picture), max);
            }
        }

        return new int[]{count, max};
    }

    private int BFS(int x, int y, int m, int n, int color, boolean[][] isVisited, int[][] picture) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        int count = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || m <= nx || n <= ny) continue;
                if(picture[nx][ny] != color || isVisited[nx][ny]) continue;

                count += 1;
                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }

        return count;
    }

    @Test
    void test() {
        assertArrayEquals(solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}}), new int[]{4, 5});
    }
}
