import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_3190_뱀 {
    static final int SNAKE = -1, APPLE = 2, EMPTY = 0;
    static int N, K, L, answer, dir;
    static int[][] board;
    static int[] head;
    static Queue<int[]> tails;
    static int[][] changeDir;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        board = new int[N][N];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = APPLE;
        }

        board[0][0] = SNAKE;
        head = new int[]{0, 0};
        tails = new LinkedList<>();
        tails.add(head);

        L = Integer.parseInt(br.readLine());
        changeDir = new int[L][2];
        for (int l = 0; l < L; l++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int d = st.nextToken().equals("D") ? 1 : -1;

            changeDir[l] = new int[]{time, d};
        }

        solution();

        System.out.println(answer);
    }

    private static void solution() {
        int dIdx = 0;
        dir = 0;
        while (true) {
            answer += 1;

            int nx = head[0] + dx[dir];
            int ny = head[1] + dy[dir];

            if (nx < 0 || ny < 0 || N <= nx || N <= ny) return;
            if (board[nx][ny] == SNAKE) return;

            if (board[nx][ny] == EMPTY) {
                int[] tail = tails.poll();
                board[tail[0]][tail[1]] = EMPTY;
            }

            board[nx][ny] = SNAKE;
            head = new int[]{nx, ny};
            tails.add(head);

            if (dIdx < L && answer == changeDir[dIdx][0]) {
                dir += changeDir[dIdx][1];
                if(dir == 4) dir = 0;
                else if(dir == -1) dir = 3;

                dIdx += 1;
            }
        }
    }
}
