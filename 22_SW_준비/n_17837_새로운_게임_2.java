import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class n_17837_새로운_게임_2 {
    static class piece {
        int x;
        int y;
        int dir;

        public piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static final int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static int[][] colors;
    static piece[] pieces;
    static List<Integer>[][] chessBoard;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        colors = new int[N][N];
        pieces = new piece[K + 1];
        chessBoard = new List[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colors[i][j] = Integer.parseInt(st.nextToken());
                chessBoard[i][j] = new ArrayList<>();
            }
        }

        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            pieces[k] = new piece(x, y, dir);
            chessBoard[x][y].add(k);
        }

        for (int time = 1; time <= 1000; time++) {
            for (int i = 1; i <= K; i++) {
                movePieces(i);
                if (isEnd()) {
                    System.out.println(time);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(chessBoard[i][j].size() > 3) return true;
            }
        }
        return false;
    }

    private static void movePieces(int num) {
        piece cur = pieces[num];

        List<Integer> list = chessBoard[cur.x][cur.y];
        int index = list.indexOf(num);
        int nx = cur.x + dx[cur.dir];
        int ny = cur.y + dy[cur.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || colors[nx][ny] == BLUE) {
            if(cur.dir == 1) cur.dir = 2;
            else if(cur.dir == 2) cur.dir = 1;
            else if(cur.dir == 3) cur.dir = 4;
            else cur.dir = 3;

            nx = cur.x + dx[cur.dir];
            ny = cur.y + dy[cur.dir];
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && colors[nx][ny] != BLUE) {
            moveByColors(num, cur, nx, ny);
        }
    }

    private static void moveByColors(int num, piece cur, int x, int y) {
        List<Integer> from = chessBoard[cur.x][cur.y];
        List<Integer> to = chessBoard[x][y];

        int index = from.indexOf(num);

        if (colors[x][y] == WHITE) {
            for (int i = index; i < from.size(); i++) {
                int p = from.get(i);
                pieces[p].x = x;
                pieces[p].y = y;
                to.add(from.get(i));
            }
            for (int i = from.size() - 1; i >= index; i--) {
                from.remove(i);
            }
        } else {
            for (int i = from.size() - 1; i >= index; i--) {
                int p = from.get(i);
                pieces[p].x = x;
                pieces[p].y = y;
                to.add(from.get(i));
            }
            for (int i = from.size() - 1; i >= index; i--) {
                from.remove(i);
            }
        }
    }
}
