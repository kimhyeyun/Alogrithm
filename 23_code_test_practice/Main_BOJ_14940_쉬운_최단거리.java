import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_14940_쉬운_최단거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[] start = new int[2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) start = new int[]{i, j};
            }
        }

        int[][] dist = new int[N][M];
        BFS(map, dist, start, N, M);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    if(map[i][j] == 0) System.out.print(0 + " ");
                    else System.out.print(-1 + " ");
                }
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void BFS(int[][] map, int[][] dist, int[] start, int N, int M) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        q.add(new int[]{start[0], start[1], 0});
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(map[nx][ny] == 0 || dist[nx][ny] <= cur[2] + 1) continue;

                dist[nx][ny] = cur[2] + 1;
                q.add(new int[]{nx, ny, cur[2] + 1});
            }
        }
    }
}
