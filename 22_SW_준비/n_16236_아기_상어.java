import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_16236_아기_상어 {
    static int N, answer;
    static int[] shark, min;
    static int[][] space, dist;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        space = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if(space[i][j] == 9){
                    shark = new int[]{i, j, 2, 0};
                    space[i][j] = 0;
                }
            }
        }

        answer = 0;
        while (true) {
            dist = new int[N][N];
            min = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

            checkDist();
            if(!eatFish()) break;
        }

        System.out.println(answer);

    }

    private static boolean eatFish() {
        if(min[0] == Integer.MAX_VALUE || min[1] == Integer.MAX_VALUE) return false;

        shark[3] += 1;
        space[min[0]][min[1]] = 0;
        shark[0] = min[0];
        shark[1] = min[1];

        answer += dist[min[0]][min[1]];

        if (shark[3] == shark[2]) {
            shark[3] = 0;
            shark[2] += 1;
        }
        return true;
    }

    private static void checkDist() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{shark[0], shark[1]});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(space[nx][ny] > shark[2] || dist[nx][ny] != 0) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;
                if (space[nx][ny] != 0 && space[nx][ny] < shark[2]) {
                    if (min[2] > dist[nx][ny]) {
                        min = new int[]{nx, ny, dist[nx][ny]};
                    } else if (min[2] == dist[nx][ny]) {
                        if(min[0] == nx){
                            min[1] = Math.min(min[1], ny);
                        } else if (min[0] > nx) {
                            min = new int[]{nx, ny, dist[nx][ny]};
                        }
                    }
                }
                q.add(new int[]{nx, ny});
            }
        }
    }
}
