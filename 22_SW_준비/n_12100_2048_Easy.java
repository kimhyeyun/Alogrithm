import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_12100_2048_Easy {
    static int N, answer;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0);

        System.out.println(answer);
    }

    private static void solution(int cnt) {
        if (cnt == 5) {
            findMax();
            return;
        }

        int[][] copyBoard = new int[N][N];
        for(int i = 0; i < N; i++) copyBoard[i] = board[i].clone();

        for (int i = 0; i < 4; i++) {
            move(i);
            solution(cnt + 1);

            for (int j = 0; j < N; j++) board[j] = copyBoard[j].clone();
        }


    }

    private static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(answer < board[i][j]) answer = board[i][j];
            }
        }
    }

    private static void move(int dir) {
        isVisited = new boolean[N][N];
        switch (dir) {
            case 0:
                //up
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        moveBlock(i, j, dir);
                    }
                }
                break;
            case 1:
                //left
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        moveBlock(i,j,dir);
                    }
                }
                break;
            case 2:
                //right
                for (int j = N - 1; j >= 0; j--) {
                    for (int i = 0; i < N; i++) {
                        moveBlock(i, j, dir);
                    }
                }
                break;
            case 3:
                //down
                for (int i = N - 1; i >= 0; i--) {
                    for (int j = 0; j < N; j++) {
                        moveBlock(i, j, dir);
                    }
                }
        }
    }

    private static void moveBlock(int x, int y, int dir) {
        if(board[x][y] == 0) return;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) return;
            if(isVisited[nx][ny]) return;

            if(board[x][y] == board[nx][ny]){
                isVisited[nx][ny] = true;
                board[nx][ny] *= 2;
                board[x][y] = 0;
                return;
            }else if(board[nx][ny] != 0) return;

            board[nx][ny] = board[x][y];
            board[x][y] = 0;
            x = nx;
            y = ny;
        }
    }
}
