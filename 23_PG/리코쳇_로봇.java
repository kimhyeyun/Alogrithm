import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 리코쳇_로봇 {

    int START = -1, END = -2, OBSTACLE = -3;

    public int solution(String[] board) {
        int N = board.length;
        int M = board[0].length();

        int[] start = new int[2];
        int[] end = new int[2];

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    start = new int[]{i, j};
                    arr[i][j] = START;
                } else if (board[i].charAt(j) == 'G') {
                    end = new int[]{i, j};
                    arr[i][j] = END;
                } else if (board[i].charAt(j) == 'D') {
                    arr[i][j] = OBSTACLE;
                }
            }
        }

        BFS(start, end, N, M, arr);

        return arr[end[0]][end[1]] == END ? -1 : arr[end[0]][end[1]];
    }

    private void BFS(int[] start, int[] end, int N, int M, int[][] arr) {
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        boolean[][] isVisited = new boolean[N][M];
        isVisited[start[0]][start[1]] = true;

        arr[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0];
                int ny = now[1];

                while (true) {
                    nx += dx[d];
                    ny += dy[d];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (arr[nx][ny] == OBSTACLE) {
                            nx -= dx[d];
                            ny -= dy[d];
                            break;
                        }
                    } else {
                        nx -= dx[d];
                        ny -= dy[d];
                        break;
                    }
                }

                if(nx == now[0] && ny == now[1]) continue;

                if (!isVisited[nx][ny]) {
                    if (arr[nx][ny] == END) {
                        arr[nx][ny] = arr[now[0]][now[1]] + 1;
                        isVisited[nx][ny] = true;
                        q.clear();
                    } else {
                        q.add(new int[]{nx, ny});
                        arr[nx][ny] = arr[now[0]][now[1]] + 1;
                        isVisited[nx][ny] = true;
                    }
                }
            }
        }
    }


    @Test
    void test() {
        assertEquals(solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}), 7);
        assertEquals(solution(new String[]{".D.R", "....", ".G..", "...D"}), -1);

    }
}
