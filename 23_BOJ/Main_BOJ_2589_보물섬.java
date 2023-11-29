import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2589_보물섬 {
    static int N, M;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    isVisited = new boolean[N][M];
                    answer = Math.max(answer, BFS(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int val = 0;

        q.offer(new int[]{x, y, 0});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(map[nx][ny] == 'W' || isVisited[nx][ny]) continue;

                q.offer(new int[]{nx, ny, cur[2] + 1});
                isVisited[nx][ny] = true;

                val = Math.max(val, cur[2] + 1);
            }
        }
        return val;
    }
}
