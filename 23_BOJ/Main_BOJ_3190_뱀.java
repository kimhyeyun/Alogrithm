import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_3190_뱀 {
    static int N, K, L;
    static int[] head, tail;
    static Deque<int[]> snake;
    static int[][] board;
    static List<int[]> rotate;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int APPLE = 1, SNAKE = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = APPLE;
        }

        board[0][0] = SNAKE;

        L = Integer.parseInt(br.readLine());
        rotate = new LinkedList<>();

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int d = st.nextToken().equals("D") ? 0 : 1;

            rotate.add(new int[]{t, d});
        }


        int time = 0;
        int dir = 1;
        int index = 0;
        head = tail = new int[]{0, 0};
        snake = new ArrayDeque<>();
        snake.addFirst(head);

        while (true) {
            time += 1;

            int nx = head[0] + dx[dir];
            int ny = head[1] + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || board[nx][ny] == SNAKE) break;

            if(board[nx][ny] == 0){
                board[tail[0]][tail[1]] = 0;
                snake.pollLast();
            }

            head = new int[]{nx, ny};
            snake.addFirst(head);
            board[nx][ny] = SNAKE;
            tail = snake.peekLast();

            if(index < L && time == rotate.get(index)[0]){
                if(rotate.get(index)[1] == 0){
                    dir = (dir + 1) % 4;
                }else{
                    dir = dir - 1 == -1 ? 3 : dir - 1;
                }
                index += 1;
            }
        }
        System.out.println(time);
    }
}
