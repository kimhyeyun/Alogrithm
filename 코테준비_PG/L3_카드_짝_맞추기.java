import java.util.LinkedList;
import java.util.Queue;

public class L3_카드_짝_맞추기 {
    static int[] orders, pos;
    static int answer;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static int solution(int[][] board, int r, int c) {
        int cardNumber = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardNumber += 1;
                }
            }
        }

        cardNumber /= 2;
        int[] card = new int[cardNumber];
        for (int i = 0; i < cardNumber; i++) {
            card[i] = i + 1;
        }

        answer = Integer.MAX_VALUE;
        orders = new int[cardNumber];

        makeCardOrder(0, card, board, new boolean[cardNumber], r, c);

        return answer;
    }

    private static void makeCardOrder(int depth, int[] card, int [][] board, boolean[] isPicked, int r, int c) {
        if (card.length == depth) {
            int[][] tmp = new int[4][4];
            for (int i = 0; i < 4; i++) {
                System.arraycopy(board[i], 0, tmp[i], 0, 4);
            }

            int moveCount = 0;
            pos = new int[]{r, c};

            for (int i = 0; i < orders.length; i++) {
                moveCount += cardSearch(tmp, orders[i]);
                moveCount += 1;
                tmp[pos[0]][pos[1]] = 0;

                moveCount += cardSearch(tmp, orders[i]);
                moveCount += 1;
                tmp[pos[0]][pos[1]] = 0;
            }

            answer = Math.min(answer, moveCount);

            return;
        }

        for (int i = 0; i < card.length; i++) {
            if(!isPicked[i]) {
                orders[depth] = card[i];
                isPicked[i] = true;
                makeCardOrder(depth + 1, card, board,isPicked, r, c);
                isPicked[i] = false;
            }
        }
    }

    private static int cardSearch(int[][] tmpBoard, int target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isChecked = new boolean[4][4];

        int x = pos[0];
        int y = pos[1];

        isChecked[x][y] = true;
        q.add(new int[]{x, y, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (tmpBoard[now[0]][now[1]] == target) {
                pos = new int[]{now[0], now[1]};
                return now[2];
            }

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny) continue;
                if(isChecked[nx][ny]) continue;

                isChecked[nx][ny] = true;
                q.add(new int[]{nx, ny, now[2] + 1});
            }

            for (int d = 0; d < 4; d++) {
                x = now[0];
                y = now[1];

                int[] res = checkRoute(x, y, d, tmpBoard);

                int nx = res[0];
                int ny = res[1];

                if(nx == x && ny == y) continue;
                if(isChecked[nx][ny]) continue;

                isChecked[nx][ny] = true;
                q.add(new int[]{nx, ny, now[2] + 1});
            }
        }
        return 0;
    }

    private static int[] checkRoute(int x, int y, int d, int[][] tmpBoard) {
        x += dx[d];
        y += dy[d];

        while (x >= 0 && x < 4 && y >= 0 && y < 4) {
            if(tmpBoard[x][y] != 0) return new int[]{x, y};

            x += dx[d];
            y += dy[d];
        }

        return new int[]{x - dx[d], y - dy[d], 0};
    }

    public static void main(String[] args) {
        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        System.out.println(solution(board, 1, 0));
    }
}
