import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_14502_연구소 {
    static final int WALL = -1, VIRUS = 2, EMPTY = 0;
    static int N, M, answer;
    static int[][] labotory;
    static boolean[][] isSpread;
    static List<int[]> viruses;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MIN_VALUE;

        labotory = new int[N][M];
        isSpread = new boolean[N][M];

        viruses = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                labotory[i][j] = Integer.parseInt(st.nextToken());

                if(labotory[i][j] == VIRUS) viruses.add(new int[]{i, j});
            }
        }

        DFS(0);

        System.out.println(answer);
    }

    private static void DFS(int cnt) {
        if (cnt == 3) {
            answer = Math.max(answer, findSafeAreaSize());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(isSpread[i][j] || labotory[i][j] != EMPTY) continue;

                isSpread[i][j] = true;
                labotory[i][j] = WALL;
                DFS(cnt + 1);
                isSpread[i][j] = false;
                labotory[i][j] = EMPTY;
            }
        }
    }

    private static int findSafeAreaSize() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(labotory[i], 0, tmp[i], 0, M);
        }

        Queue<int[]> q = new LinkedList<>(viruses);


        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || tmp[nx][ny] != EMPTY) continue;

                tmp[nx][ny] = VIRUS;
                q.add(new int[]{nx, ny});
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == EMPTY) cnt += 1;
            }
        }

        return cnt;
    }
}
