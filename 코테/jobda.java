import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class jobda {
    long answer = Long.MIN_VALUE;
    long[][] map;

    public long solution(int n, int m, long[][] a) {
        map = new long[n + 2][m];
        for(int i = 1; i <= n; i++){
            for(int j = 0;j<m;j++){
                map[i][j] = a[i-1][j];
            }
        }
        for (int j = 0; j < m; j++) {
            boolean[][] isVisited = new boolean[n + 2][m];
            isVisited[1][j] = true;

            findTreasure(n, m, 1, j, a, isVisited, map[1][j]);
        }

        return answer;
    }

    public void findTreasure(int n, int m, int x, int y, long[][] a, boolean[][] isVisited, long sum) {
        if (x == n + 1) {
            answer = Math.max(answer, sum);
            System.out.println(answer);
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(isVisited[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        if (isPossible(n, m, x + 1, y, isVisited)) {
            isVisited[x + 1][y] = true;
            findTreasure(n, m, x + 1, y, a, isVisited, sum + map[x + 1][y]);
            isVisited[x + 1][y] = false;
        }

        if (isPossible(n, m, x, y + 1, isVisited)) {
            isVisited[x][y + 1] = true;
            findTreasure(n, m, x, y + 1, a, isVisited, sum + map[x][y + 1]);
            isVisited[x][y + 1] = false;
        }

        if (isPossible(n, m, x, y - 1, isVisited)) {
            isVisited[x][y - 1] = true;
            findTreasure(n, m, x, y - 1, a, isVisited, sum + map[x][y - 1]);
            isVisited[x][y - 1] = false;
        }

        return;
    }

    public boolean isPossible(int n, int m, int x, int y, boolean[][] isVisited) {
        if (x < 1 || y < 0 || n + 1 < x || m <= y || isVisited[x][y]) return false;

        isVisited[x][y] = true;

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 1 || ny < 0 || n + 1 < nx || m <= ny) continue;
                    if(isVisited[nx][ny]) count += 1;
                }

                if(count >= 3){
                    isVisited[x][y] = false;
                    return false;
                }

            }
        }

        isVisited[x][y] = false;
        return true;
    }

    @Test
    public void test() {
        assertEquals(solution(3, 3, new long[][]{{1, 4, 5}, {3, 8, 2}, {10, 0, 6}}), 30);
    }
}
