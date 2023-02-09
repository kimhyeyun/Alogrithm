import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_14940_쉬운_최단거리 {
    static int N, M;
    static int[][] map, dist;
    static int[] start;
    static List<int[]> noGo;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        noGo = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) start = new int[]{i, j};
                else if(map[i][j] == 0) noGo.add(new int[]{i, j});
            }
        }

        sol();

        for (int[] l : noGo) {
            dist[l[0]][l[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(dist[i][j] == Integer.MAX_VALUE) System.out.print(-1 + " ");
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void sol() {
        Queue<int[]> q = new LinkedList<>();
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[start[0]][start[1]] = 0;
        q.add(start);

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(map[nx][ny] == 0) continue;
                if(dist[nx][ny] <= dist[now[0]][now[1]] + 1) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
