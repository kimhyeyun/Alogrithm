import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1600_말이_되고픈_원숭이 {
    static final int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int K, W, H;
    static int[][] board;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isVisited = new boolean[H][W][K + 1];

        int answer = BFS(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static int BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y, 0, K});
        isVisited[x][y][K] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == H - 1 && cur[1] == W - 1) return cur[2];

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || H <= nx || W <= ny) continue;
                if(isVisited[nx][ny][cur[3]] || board[nx][ny] == 1) continue;

                isVisited[nx][ny][cur[3]] = true;
                q.offer(new int[]{nx, ny, cur[2] + 1, cur[3]});
            }

            if (cur[3] > 0) {
                for (int d = 0; d < 8; d++) {
                    int nx = cur[0] + hdx[d];
                    int ny = cur[1] + hdy[d];

                    if(nx < 0 || ny < 0 || H <= nx || W <= ny) continue;
                    if(isVisited[nx][ny][cur[3] - 1] || board[nx][ny] == 1) continue;

                    isVisited[nx][ny][cur[3] - 1] = true;
                    q.offer(new int[]{nx, ny, cur[2] + 1, cur[3] - 1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
