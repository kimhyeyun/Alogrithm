import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_18500_미네랄_2 {
    static int R, C, N;
    static char[][] cave;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static List<int[]> clusters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        cave = new char[R][C];
        for (int i = 0; i < R; i++) {
            cave[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(stringTokenizer.nextToken());
            height = R - height;

            if (i % 2 == 0) {
                for (int c = 0; c < C; c++) {
                    if (cave[height][c] == 'x') {
                        cave[height][c] = '.';
                        break;
                    }
                }
            } else {
                for (int c = C - 1; c >= 0; c--) {
                    if (cave[height][c] == 'x') {
                        cave[height][c] = '.';
                        break;
                    }
                }
            }

            findCluster();
            if(clusters.size() > 0) dropCluster();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }
    }

    private static void dropCluster() {
        for (int[] c : clusters) {
            cave[c[0]][c[1]] = '.';
        }

        int h = 0;
        LOOP: for (int i = 1; i < R; i++) {
            for (int[] c : clusters) {
                if(c[0] + i >= R || cave[c[0]+i][c[1]] == 'x') break LOOP;
            }
            h = i;
        }

        for (int[] c : clusters) {
            cave[c[0] + h][c[1]] = 'x';
        }
    }

    private static void findCluster() {
        clusters = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        isVisited = new boolean[R][C];

        for (int c = 0; c < C; c++) {
            q.add(new int[]{R - 1, c});
            isVisited[R - 1][c] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny || isVisited[nx][ny] || cave[nx][ny] == '.') continue;

                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(cave[i][j] == 'x' && !isVisited[i][j]) clusters.add(new int[]{i, j});

            }
        }
    }
}
