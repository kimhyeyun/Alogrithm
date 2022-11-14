import java.util.LinkedList;
import java.util.Queue;

public class L3_블록_이동하기 {
    static class Robot {
        int x;
        int y;
        int dir;
        int count;

        public Robot(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }
    static int n, answer;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] rdx = {{-1, 0, -1, 0}, {0, 0, 1, 1}};
    static int[][] rdy = {{0, 0, 1, 1}, {-1, 0, -1, 0}};
    static boolean[][][] isVisited;

    public static int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        n = board.length;
        isVisited = new boolean[2][n + 1][n + 1];

        start(board);

        return answer;
    }

    private static void start(int[][] board) {
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(0, 0, 0, 0));
        isVisited[0][0][0] = true;

        while (!q.isEmpty()) {
            Robot now = q.poll();

            if (now.dir == 0 && now.x == n - 1 && now.y == n - 2) {
                answer = Math.min(answer, now.count);
                continue;
            } else if (now.dir == 1 && now.x == n - 2 && now.y == n - 1) {
                answer = Math.min(answer, now.count);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (!isPossibleMove(nx, ny, now.dir, board)) {
                    continue;
                }

                if (!isVisited[now.dir][nx][ny]) {
                    q.add(new Robot(nx, ny, now.dir, now.count + 1));
                    isVisited[now.dir][nx][ny] = true;
//                    System.out.println(nx + " " + ny + " " + now.dir);
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + rdx[now.dir][d];
                int ny = now.y + rdy[now.dir][d];

                int sx = now.x + dx[d % 2];
                int sy = now.y + dy[d % 2];

                if (now.dir == 1) {
                    sx = now.x + dx[d < 2 ? d + 2 : d];
                    sy = now.y + dy[d < 2 ? d + 2 : d];
                }

                int ndir = now.dir ^ 1;

                if (!isPossibleMove(nx, ny, ndir, board) || !isPossibleMove(sx, sy, now.dir, board)) {
                    continue;
                }

                if (!isVisited[ndir][nx][ny]) {
                    q.add(new Robot(nx, ny, ndir, now.count + 1));
                    isVisited[ndir][nx][ny] = true;
//                    System.out.println("rotate" + " " + now.x + " "+ now.y+ "    " + nx + " " + ny + " " + ndir);
                }
            }
        }
        return;
    }

    private static boolean isPossibleMove(int x, int y, int dir, int[][] board) {
        int n = board.length;

        if (dir == 0) {
            if (x < 0 || y < 0 || n <= x || n <= y + 1 || board[x][y] == 1 || board[x][y + 1] == 1) {
                return false;
            }
            return true;
        } else {
            if (x < 0 || y < 0 || n <= x + 1 || n <= y || board[x][y] == 1 || board[x + 1][y] == 1) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(solution(board));

    }
}
