import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1726_로봇 {
    static int N, M;
    static int curX, curY, dir;
    static int destX, destY, destDir;
    static int[][] board;
    static boolean[][][] isVisited;
    static final int[] dx = {0, 0, 0, 1, -1};
    static final int[] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isVisited = new boolean[M][N][5];

        board = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        curX = Integer.parseInt(st.nextToken()) - 1;
        curY = Integer.parseInt(st.nextToken()) - 1;
        dir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        destX = Integer.parseInt(st.nextToken()) - 1;
        destY = Integer.parseInt(st.nextToken()) - 1;
        destDir = Integer.parseInt(st.nextToken());

        System.out.println(BFS());

    }

    private static int BFS() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{curX, curY, dir, 0});
        isVisited[curX][curY][dir] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == destX && cur[1] == destY && cur[2] == destDir) {
                return cur[3];
            }

            for (int k = 1; k <= 3; k++) {
                // 직진
                int nx = cur[0] + (dx[cur[2]] * k);
                int ny = cur[1] + (dy[cur[2]] * k);

                if(nx < 0 || ny < 0 || M <= nx || N <= ny) continue;
                if(board[nx][ny] == 1) break;

                if (!isVisited[nx][ny][cur[2]]) {
                    isVisited[nx][ny][cur[2]] = true;
                    q.add(new int[]{nx, ny, cur[2], cur[3] + 1});
                }
            }

            // 회전
            int left = 0, right = 0;

            switch (cur[2]) {
                case 1: left = 4; right = 3; break;
                case 2: left = 3; right = 4; break;
                case 3: left = 1; right = 2; break;
                case 4: left = 2; right = 1; break;
            }

            if (!isVisited[cur[0]][cur[1]][left]) {
                isVisited[cur[0]][cur[1]][left] = true;
                q.add(new int[]{cur[0], cur[1], left, cur[3] + 1});
            }

            if (!isVisited[cur[0]][cur[1]][right]) {
                isVisited[cur[0]][cur[1]][right] = true;
                q.add(new int[]{cur[0], cur[1], right, cur[3] + 1});
            }
        }

        return -1;
    }
}
