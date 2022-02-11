package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2178_미로탐색 {
    static int N, M;
    static char[][] maze;
    static int[][] time;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        maze = new char[N][M];
        time = new int[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        BFS(0, 0);

        System.out.println(time[N-1][M-1]);
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        time[x][y] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || maze[nx][ny] == '0') continue;

                if (time[nx][ny] == 0 || time[nx][ny] > time[now[0]][now[1]] + 1) {
                    q.offer(new int[]{nx, ny});
                    time[nx][ny] = time[now[0]][now[1]] + 1;
                }
            }
        }
    }
}
