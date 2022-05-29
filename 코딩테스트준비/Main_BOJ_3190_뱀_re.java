import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_3190_뱀_re {
    static int N, K, L;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<int[]> dirChange;
    static Queue<int[]> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            board[x][y] = 2;
        }

        dirChange = new ArrayList<>();
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(stringTokenizer.nextToken());
            int d = stringTokenizer.nextToken().equals("D") ? 1 : -1;

            dirChange.add(new int[]{t, d});
        }

        snake = new LinkedList<>();
        board[0][0] = -1;

        int[] head = new int[]{0, 0};
        int time = 0, dirCnt = 0, curDir = 0;
        snake.add(head);

        while (true) {
            time += 1;

            int nx = head[0] + dx[curDir];
            int ny = head[1] + dy[curDir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || board[nx][ny] == -1) break;

            if (board[nx][ny] != 2) {
                int[] tail = snake.poll();
                board[tail[0]][tail[1]] = 0;
            }

            head = new int[]{nx, ny};
            snake.add(head);
            board[nx][ny] = -1;

            if (dirCnt < L && dirChange.get(dirCnt)[0] == time) {
                curDir = (curDir + dirChange.get(dirCnt)[1]) % 4;
                curDir = curDir == -1 ? 3 : curDir;
                dirCnt += 1;
            }
        }
        System.out.println(time);
    }
}
