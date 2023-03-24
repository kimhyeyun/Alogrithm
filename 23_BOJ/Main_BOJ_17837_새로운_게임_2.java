import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17837_새로운_게임_2 {
    static class Horse {
        int x, y;
        int dir;
        public Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, K;
    static int[][] colorOfBoard;
    static List<Integer>[][] board;
    static Horse[] horses;

    static final int WHITE = 0, RED = 1, BLUE = 2;
    static final int[] dx = {0, 0, 0, -1, 1};
    static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        colorOfBoard = new int[N][N];
        board = new List[N][N];
        horses = new Horse[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colorOfBoard[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new LinkedList<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            board[x][y].add(i);
            horses[i] = new Horse(x, y, d);
        }

        int answer = 0;
        while (answer++ <= 1000) {
            for (int i = 1; i <= K; i++) {
                moveHorse(i);

                if (isEnd()) {
                    System.out.println(answer);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j].size() >= 4) return true;
            }
        }
        return false;
    }

    private static void moveHorse(int index) {
        Horse now = horses[index];

        int nx = now.x + dx[now.dir];
        int ny = now.y + dy[now.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || colorOfBoard[nx][ny] == BLUE) {
            if(now.dir == 1) now.dir = 2;
            else if(now.dir == 2) now.dir = 1;
            else if(now.dir == 3) now.dir = 4;
            else now.dir = 3;

            nx = now.x + dx[now.dir];
            ny = now.y + dy[now.dir];
        }

        if(nx < 0 || ny < 0 || N <= nx || N <= ny || colorOfBoard[nx][ny] == BLUE) return;

        if (colorOfBoard[nx][ny] == WHITE) {
            moveToWhite(index, now, nx, ny);
        } else {
            moveToRed(index, now, nx, ny);
        }
    }

    private static void moveToRed(int number, Horse now, int x, int y) {
        List<Integer> from = board[now.x][now.y];
        List<Integer> to = board[x][y];

        int index = from.indexOf(number);

        for (int i = from.size() - 1; i >= index; i--) {
            int h = from.get(i);

            horses[h].x = x;
            horses[h].y = y;

            to.add(h);
        }

        for (int i = from.size() - 1; i >= index; i--) {
            from.remove(i);
        }
    }

    private static void moveToWhite(int number, Horse now, int x, int y) {
        List<Integer> from = board[now.x][now.y];
        List<Integer> to = board[x][y];

        int index = from.indexOf(number);

        for (int i = index; i < from.size(); i++) {
            int h = from.get(i);

            horses[h].x = x;
            horses[h].y = y;

            to.add(h);
        }

        for (int i = from.size() - 1; i >= index; i--) {
            from.remove(i);
        }
    }
}
