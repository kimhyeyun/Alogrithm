import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17837_새로운_게임_2_re {

    static class Piece {
        int x, y, dir;

        public Piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static final int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static int[][] colors;
    static Piece[] pieces;
    static List<Integer>[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        pieces = new Piece[K];
        board = new List[N][N];
        colors = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colors[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            pieces[i] = new Piece(x, y, d);
            board[x][y].add(i);
        }

        gameStart();
    }

    private static void gameStart() {
        for (int time = 1; time <= 1000; time++) {
            for (int i = 0; i < K; i++) {
                move(i);
                if (isEnd()) {
                    System.out.println(time);
                    return;
                }
            }
        }
        System.out.println(-1);
        return;
    }

    private static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j].size() > 3) return true;
            }
        }
        return false;
    }

    private static void move(int num) {
        Piece cur = pieces[num];

        int nx = cur.x + dx[cur.dir];
        int ny = cur.y + dy[cur.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || colors[nx][ny] == BLUE) {
            changeDir(cur);
            nx = cur.x + dx[cur.dir];
            ny = cur.y + dy[cur.dir];
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && colors[nx][ny] != BLUE) {
            moveByColor(num, cur, nx, ny);
        }
    }

    private static void moveByColor(int num, Piece cur, int x, int y) {
        List<Integer> from = board[cur.x][cur.y];
        List<Integer> to = board[x][y];

        int heightOfPiece = from.indexOf(num);

        if (colors[x][y] == WHITE) {
            for (int i = heightOfPiece; i < from.size(); i++) {
                int p = from.get(i);
                updatePiece(x, y, p);
                to.add(p);
            }
            removePiece(from, heightOfPiece, from.size());
        } else {
            for (int i = from.size() - 1; i >= heightOfPiece; i--) {
                int p = from.get(i);
                updatePiece(x, y, p);
                to.add(p);
            }
            removePiece(from, heightOfPiece, from.size());
        }
    }

    private static void removePiece(List<Integer> from, int bottom, int top) {
        for (int i = top - 1; i >= bottom; i--) {
            from.remove(i);
        }
    }

    private static void updatePiece(int x, int y, int p) {
        pieces[p].x = x;
        pieces[p].y = y;
    }

    private static void changeDir(Piece cur) {
        if(cur.dir == 0) cur.dir = 1;
        else if(cur.dir == 1) cur.dir = 0;
        else if(cur.dir == 2) cur.dir = 3;
        else cur.dir = 2;
    }
}
