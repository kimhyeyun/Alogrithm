public class L3_사라지는_발판 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int N, M;

    static class Result{
        boolean isWin;
        int count;

        public Result(boolean isWin, int count) {
            this.isWin = isWin;
            this.count = count;
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;

        return DFS(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, board).count;
    }

    private Result DFS(int ax, int ay, int bx, int by, int aDepth, int bDepth, int[][] board) {
        boolean win = false;
        int winCount = 5 * 5;
        int loseCount = aDepth + bDepth;

        // A 움직임
        if (aDepth == bDepth && board[ax][ay] == 1) {
            for (int d = 0; d < 4; d++) {
                int nx = ax + dx[d];
                int ny = ay + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || board[nx][ny] == 0) continue;
                board[ax][ay] = 0;
                Result result = DFS(nx, ny, bx, by, aDepth + 1, bDepth, board);

                win |= !result.isWin;

                if(result.isWin) loseCount = Math.max(loseCount, result.count);
                else winCount = Math.min(winCount, result.count);

                board[ax][ay] = 1;
            }
        } else if (aDepth > bDepth && board[bx][by] == 1) {
            for (int d = 0; d < 4; d++) {
                int nx = bx + dx[d];
                int ny = by + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || board[nx][ny] == 0) continue;
                board[bx][by] = 0;
                Result result = DFS(ax, ay, nx, ny, aDepth, bDepth + 1, board);

                win |= !result.isWin;

                if(result.isWin) loseCount = Math.max(loseCount, result.count);
                else winCount = Math.min(winCount, result.count);

                board[bx][by] = 1;
            }
        }
        return new Result(win, win ? winCount : loseCount);
    }
}
