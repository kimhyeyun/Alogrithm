import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 카카오_프렌즈_컬러링북 {

    public int[] solution(int m, int n, int[][] picture) {

        boolean[][] isVisited = new boolean[m][n];
        int countOfAreas = 0, countOfMaxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(isVisited[i][j] || picture[i][j] == 0) continue;

                isVisited[i][j] = true;
                countOfMaxArea = Math.max(countOfMaxArea, BFS(i, j, picture, isVisited));
                countOfAreas += 1;
            }
        }

        return new int[]{countOfAreas, countOfMaxArea};
    }

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    private int BFS(int x, int y, int[][] picture, boolean[][] isVisited) {
        Queue<int[]> q = new LinkedList<>();

        int color = picture[x][y];
        int n = picture.length;
        int m = picture[0].length;
        int area = 1;

        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || n <= nx || m <= ny) continue;
                if(isVisited[nx][ny] || picture[nx][ny] != color) continue;

                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;

                area += 1;
            }
        }
        return area;
    }

    @Test
    void test() {
        assertArrayEquals(solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}}), new int[]{4, 5});
    }
}
