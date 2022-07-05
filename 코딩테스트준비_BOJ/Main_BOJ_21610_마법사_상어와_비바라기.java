import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_21610_마법사_상어와_비바라기 {
    static int N, M, d, s;
    static int[][] WaterOfBucket;
    static boolean[][] isVisited;
    static List<int[]> cloud;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        WaterOfBucket = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                WaterOfBucket[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        cloud = new ArrayList<>();
        cloud.add(new int[]{N-2,0});
        cloud.add(new int[]{N-2,1});
        cloud.add(new int[]{N-1,0});
        cloud.add(new int[]{N-1,1});

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            d = Integer.parseInt(stringTokenizer.nextToken());
            s = Integer.parseInt(stringTokenizer.nextToken());

            isVisited = new boolean[N][N];
            MoveCloud(d, s);
            checkDiagonal();
            makeCloud();
        }

        int result = calOfWater();
        System.out.println(result);
    }

    private static int calOfWater() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += WaterOfBucket[i][j];
            }
        }
        return sum;
    }

    private static void makeCloud() {
        cloud = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (WaterOfBucket[i][j] >= 2 && !isVisited[i][j]) {
                    WaterOfBucket[i][j] -= 2;
                    cloud.add(new int[]{i, j});
                }
            }
        }
    }

    private static void checkDiagonal() {
        for (int[] c : cloud) {
            int cnt = 0;
            for (int d = 2; d < 9; d += 2) {
                int nx = c[0] + dx[d];
                int ny = c[1] + dy[d];

                if (nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(WaterOfBucket[nx][ny] > 0) cnt++;
            }
            WaterOfBucket[c[0]][c[1]] += cnt;
        }
    }

    private static void MoveCloud(int d, int s) {
        for (int[] c : cloud) {
            int nx = (c[0] + N + dx[d] * s % N) % N;
            int ny = (c[1] + N + dy[d] * s % N) % N;

            isVisited[nx][ny] = true;
            WaterOfBucket[nx][ny] += 1;
            c[0] = nx;
            c[1] = ny;
        }
    }
}
