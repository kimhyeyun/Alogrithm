import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16236_아기_상어 {
    static int N;
    static int[][] map;
    static int[] shark, min;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9) {
                    shark = new int[]{i, j, 2, 0};
                    map[i][j] = 0;
                }
            }
        }

        int answer = 0;

        while (true) {
            min = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

            if(eatFish()) answer += min[2];
            else break;
        }
        System.out.println(answer);
    }

    private static boolean eatFish() {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[N][N];

        q.add(new int[]{shark[0], shark[1], 0});
        dist[shark[0]][shark[1]] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] > shark[2]) continue;
                if(dist[nx][ny] != 0) continue;

                dist[nx][ny] = now[2] + 1;
                q.add(new int[]{nx, ny, dist[nx][ny]});

                if (map[nx][ny] != 0 && map[nx][ny] < shark[2]) {
                    if(min[2] > dist[nx][ny]) min = new int[]{nx, ny, dist[nx][ny]};
                    else if (min[2] == dist[nx][ny]) {
                        if(min[0] == nx) min[1] = Math.min(min[1], ny);
                        else if(min[0] > nx) min = new int[]{nx, ny, dist[nx][ny]};
                    }
                }
            }
        }

        if(min[2] == Integer.MAX_VALUE) return false;

        int size = shark[2];
        int count = shark[3] + 1;
        if(size == count) {
            size += 1;
            count = 0;
        }

        shark = new int[]{min[0], min[1], size, count};
        map[min[0]][min[1]] = 0;

        return true;
    }
}
