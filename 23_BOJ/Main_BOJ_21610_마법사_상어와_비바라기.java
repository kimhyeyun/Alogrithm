import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_21610_마법사_상어와_비바라기 {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static List<int[]> clouds;

    static final int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            isVisited = new boolean[N][N];

            MoveClouds(d, s);
            checkDiagonal();
            makeClouds();
        }

        System.out.println(totalOfWater());

    }

    private static int totalOfWater() {
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }
        return answer;
    }

    private static void makeClouds() {
        clouds = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !isVisited[i][j]) {
                    map[i][j] -= 2;
                    clouds.add(new int[]{i, j});
                }
            }
        }
    }

    private static void checkDiagonal() {
        for (int[] cloud : clouds) {
            int count = 0;

            for (int d = 2; d < 9; d += 2) {
                int nx = cloud[0] + dx[d];
                int ny = cloud[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] < 1) continue;

                count += 1;
            }

            map[cloud[0]][cloud[1]] += count;
        }
    }

    private static void MoveClouds(int dir, int speed) {
        for (int[] cloud : clouds) {
            int nx = (cloud[0] + N + dx[dir] * speed % N) % N;
            int ny = (cloud[1] + N + dy[dir] * speed % N) % N;

            isVisited[nx][ny] = true;
            map[nx][ny] += 1;

            cloud[0] = nx;
            cloud[1] = ny;
        }
    }
}
