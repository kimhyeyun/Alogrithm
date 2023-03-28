import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_23288_주사위_굴리기_2 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] hOfDice = new int[]{4, 1, 3, 6};
        int[] vOfDice = new int[]{2, 1, 5, 6};
        int[] now = new int[]{0, 0};

        int dir = 1;
        int answer = 0;

        while (K-- > 0) {
            int nx = now[0] + dx[dir];
            int ny = now[1] + dy[dir];

            if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                if(dir == 0) dir = 2;
                else if(dir == 1) dir = 3;
                else if(dir == 2) dir = 0;
                else dir = 1;

                nx = now[0] + dx[dir];
                ny = now[1] + dy[dir];
            }

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

            if (dir == 0) {
                int t = vOfDice[0];

                for (int i = 0; i < 3; i++) {
                    vOfDice[i] = vOfDice[i + 1];
                }
                vOfDice[3] = t;

                hOfDice[1] = vOfDice[1];
                hOfDice[3] = vOfDice[3];
            } else if (dir == 1) {
                int t = hOfDice[3];

                for (int i = 3; i > 0; i--) {
                    hOfDice[i] = hOfDice[i - 1];
                }
                hOfDice[0] = t;

                vOfDice[1] = hOfDice[1];
                vOfDice[3] = hOfDice[3];
            } else if (dir == 2) {
                int t = vOfDice[3];

                for (int i = 3; i > 0; i--) {
                    vOfDice[i] = vOfDice[i - 1];
                }
                vOfDice[0] = t;

                hOfDice[1] = vOfDice[1];
                hOfDice[3] = vOfDice[3];
            } else {
                int t = hOfDice[0];

                for (int i = 0; i < 3; i++) {
                    hOfDice[i] = hOfDice[i + 1];
                }
                hOfDice[3] = t;

                vOfDice[1] = hOfDice[1];
                vOfDice[3] = hOfDice[3];
            }

            int A = vOfDice[3];
            int B = map[nx][ny];

            boolean[][] isPossible = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();

            isPossible[nx][ny] = true;
            q.add(new int[]{nx, ny});

            int count = 1;

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nextX = cur[0] + dx[d];
                    int nextY = cur[1] + dy[d];

                    if(nextX < 0 || nextY < 0 || N <= nextX || M <= nextY) continue;
                    if(isPossible[nextX][nextY] || B != map[nextX][nextY]) continue;

                    count += 1;
                    q.add(new int[]{nextX, nextY});
                    isPossible[nextX][nextY] = true;
                }
            }

            answer += B * count;

            if(A > B) dir = (dir + 1) % 4;
            else if(A < B) dir = (dir + 3) % 4;

            now = new int[]{nx, ny};
        }

        System.out.println(answer);
    }

}
