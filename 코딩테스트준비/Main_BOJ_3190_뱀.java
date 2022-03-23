import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_3190_뱀 {
    static int N, K, L;
    static int[][] map;
    static List<int[]> DirList;
    static Queue<int[]> snake;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        DirList = new ArrayList<>();
        snake = new LinkedList<>();

        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            map[x - 1][y - 1] = 2;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(stringTokenizer.nextToken());
            int dir = stringTokenizer.nextToken().equals("D") ? 1 : -1;

            DirList.add(new int[]{time, dir});
        }

        int time = 0, turn = 0;
        int curDir = 0;
        map[0][0] = -1;
        int[] head = new int[]{0, 0};
        snake.add(head);

        while (true) {
            time += 1;

            int nx = head[0] + dx[curDir];
            int ny = head[1] + dy[curDir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] == -1) break;

            if (map[nx][ny] != 2) {
                int[] tail = snake.poll();
                map[tail[0]][tail[1]] = 0;
            }

            head = new int[]{nx, ny};
            snake.add(head);
            map[nx][ny] = -1;

            if (turn < L && time == DirList.get(turn)[0]) {
                curDir = (curDir + DirList.get(turn)[1]) % 4;
                curDir = curDir == -1 ? 3 : curDir;
                turn += 1;
            }
        }

        System.out.println(time);

    }
}
