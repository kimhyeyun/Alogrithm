import java.util.LinkedList;
import java.util.Queue;

public class L2_카카오프렌즈_컬러링북 {
    boolean[][] isVisited;
    int maxSizeOfArea, cntOfAreas;
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    public int[] solution(int m, int n, int[][] picture) {
        isVisited = new boolean[m][n];
        maxSizeOfArea = 0;
        cntOfAreas = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(picture[i][j] == 0) continue;
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    int cnt = BFS(i, j, picture, m, n);

                    cntOfAreas += 1;
                    maxSizeOfArea = Math.max(maxSizeOfArea, cnt);
                }
            }
        }

        return new int[]{cntOfAreas, maxSizeOfArea};
    }

    private int BFS(int x, int y, int[][] picture, int m, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || m <= nx || n <= ny || isVisited[nx][ny]) continue;
                if(picture[nx][ny] == 0) continue;
                if(picture[cur[0]][cur[1]] != picture[nx][ny]) continue;

                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                cnt += 1;
            }
        }
        return cnt;
    }
}
