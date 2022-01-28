import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16236_아기_상어 {

    static int N, sharkX, sharkY, minX, minY, minDist, sharkSize = 2, eating = 0, time = 0;
    static int[][] map, dist;
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
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true){
            dist = new int[N][N];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            checkMinDist(sharkX, sharkY);
            if(eatFish()) break;
        }
        System.out.println(time);
    }

    private static boolean eatFish() {
        if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
            eating++;
            map[minX][minY] = 0;
            sharkX = minX;
            sharkY = minY;
            time += dist[minX][minY];

            if (eating == sharkSize) {
                sharkSize++;
                eating = 0;
            }
            return false;
        }
        return true;
    }

    private static void checkMinDist(int sharkX, int sharkY) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sharkX, sharkY});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] > sharkSize || dist[nx][ny] != 0) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;


                if(map[nx][ny] != 0 && map[nx][ny] < sharkSize){
                    if(minDist > dist[nx][ny]){
                        minDist = dist[nx][ny];
                        minX = nx;
                        minY = ny;
                    } else if (minDist == dist[nx][ny]) {
                        if (minX == nx) {
                            minY = Math.min(minY, ny);
                        } else if (minX > nx) {
                            minX = nx;
                            minY = ny;
                        }
                    }
                }

                q.offer(new int[]{nx, ny});
            }
        }
    }
}
