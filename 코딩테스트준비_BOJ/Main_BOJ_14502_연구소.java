import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소 {
    static int N, M, ans = Integer.MIN_VALUE;
    static int[][] lab;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        DFS(0);

        System.out.println(ans);
    }

    private static void DFS(int wallCnt) {
        if (wallCnt == 3) {
            BFS();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    DFS(wallCnt + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    private static void BFS() {
        int[][] virusLab = new int[N][M];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusLab[i][j] = lab[i][j];
                if(virusLab[i][j] == 2) q.add(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || virusLab[nx][ny] != 0) continue;

                virusLab[nx][ny] = 2;
                q.add(new int[]{nx, ny});
            }
        }

        findSafeArea(virusLab);

    }

    private static void findSafeArea(int[][] virusLab) {
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(virusLab[i][j] == 0) area += 1;
            }

        }

        ans = ans < area ? area : ans;
    }
}
