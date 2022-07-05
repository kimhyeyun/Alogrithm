import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16236_아기_상어_re {
    static int N, eating, time;
    static int[][] map, dist;
    static int[] babyShark, min;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if (map[i][j] == 9) {
                    babyShark = new int[]{i, j, 2};
                    map[i][j] = 0;
                }
            }
        }

        eating = 0;
        time = 0;

        while (true) {
            dist = new int[N][N];
            min = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

            checkDist();

            if(eatFish()) break;
        }
        System.out.println(time);
    }

    private static boolean eatFish() {
        if (min[0] != Integer.MAX_VALUE && min[1] != Integer.MAX_VALUE) {
            eating += 1;
            map[min[0]][min[1]] = 0;
            babyShark[0] = min[0];
            babyShark[1] = min[1];

            time += dist[min[0]][min[1]];

            if (eating == babyShark[2]) {
                babyShark[2] += 1;
                eating = 0;
            }
            return false;
        }
        return true;
    }

    private static void checkDist() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{babyShark[0], babyShark[1]});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(map[nx][ny] > babyShark[2] || dist[nx][ny] != 0) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;

                if (map[nx][ny] != 0 && map[nx][ny] < babyShark[2]) {
                    if (min[2] > dist[nx][ny]) {
                        min = new int[]{nx, ny, dist[nx][ny]};
                    } else if (min[2] == dist[nx][ny]) {
                        if(min[0] == nx) min[1] = Math.min(min[1], ny);
                        else if(min[0] > nx) min = new int[]{nx, ny, dist[nx][ny]};
                    }
                }
                q.add(new int[]{nx, ny});
            }
        }
    }
}
