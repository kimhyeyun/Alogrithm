import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17838_새로운_게임_2 {
    static class piece {
        int x, y, dir;

        public piece(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, K;
    static int[][] color;
    static List<Integer>[][] map;
    static piece[] pieces;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static final int WHITE = 0, RED = 1, BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new List[N][N];
        pieces = new piece[K];
        color = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            pieces[i] = new piece(x, y, d);
            map[x][y].add(i);
        }

        game();

    }

    private static void game() {
        for (int time = 1; time <= 1000; time++) {
            for (int i = 0; i < K; i++) {
                if (move(i)) {
                    System.out.println(time);
                    return;
                }

            }
        }
        System.out.println(-1);
    }

    private static boolean move(int i) {
        piece now = pieces[i];

        int nx = now.x + dx[now.dir];
        int ny = now.y + dy[now.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || color[nx][ny] == BLUE) {
            changeDir(now);
            nx = now.x + dx[now.dir];
            ny = now.y + dy[now.dir];
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && color[nx][ny] != BLUE) {
            moveByColor(i, now, nx, ny);

            if(map[nx][ny].size() > 3) return true;
        }
        return false;
    }

    private static void moveByColor(int num, piece now, int nx, int ny) {
        List<Integer> from = map[now.x][now.y];
        List<Integer> to = map[nx][ny];

        int heightOfNow = from.indexOf(num);

        switch (color[nx][ny]) {
            case WHITE:
                for (int i = heightOfNow; i < from.size(); i++) {
                    int piece = from.get(i);
                    updateList(piece, nx, ny);
                    to.add(piece);
                }

                removePiece(from, from.size() - 1, heightOfNow);
                break;

            case RED:
                for (int i = from.size() - 1; i >= heightOfNow; i--) {
                    int piece = from.get(i);
                    updateList(piece, nx, ny);
                    to.add(piece);
                }

                removePiece(from, from.size() - 1, heightOfNow);
                break;
        }
    }

    private static void removePiece(List<Integer> list, int top, int bottom) {
        for (int i = top; i >= bottom; i--) {
            list.remove(i);
        }
    }

    private static void updateList(int piece, int nx, int ny) {
        pieces[piece].x = nx;
        pieces[piece].y = ny;
    }

    private static void changeDir(piece now) {
        if(now.dir == 0) now.dir = 1;
        else if(now.dir == 1) now.dir = 0;
        else if(now.dir == 2) now.dir = 3;
        else now.dir = 2;
    }
}
