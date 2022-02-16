import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15683_감시 {

    static int N, M, ans = Integer.MAX_VALUE;
    static final int OK = 7, WALL = 6;
    static int[][] map;
    static List<int[]> cameraList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        cameraList = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(1 <= map[i][j] && map[i][j] <= 5) cameraList.add(new int[]{map[i][j], i, j});
            }
        }

        check(0, map);

        System.out.println(ans);
    }

    private static void check(int cnt, int[][] prevMap) {
        int[][] isVisited = new int[N][M];
        if (cnt == cameraList.size()) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(prevMap[i][j] == 0) tmp += 1;
                }
            }

            ans = ans > tmp ? tmp : ans;
            return;
        }

        int[] now = cameraList.get(cnt);
        int num = now[0];
        int x = now[1];
        int y = now[2];

        switch (num) {
            case 1:
                for (int d = 0; d < 4; d++) {
                    for (int i = 0; i < N; i++) {
                        System.arraycopy(prevMap[i], 0, isVisited[i], 0, M);
                    }

                    detect(isVisited, x, y, d);
                    check(cnt + 1, isVisited);
                }
                break;
            case 2:
                for (int d = 0; d < 2; d++) {
                    for (int i = 0; i < N; i++) {
                        System.arraycopy(prevMap[i], 0, isVisited[i], 0, M);
                    }
                    detect(isVisited, x, y, d);
                    detect(isVisited, x, y, d + 2);
                    check(cnt + 1, isVisited);
                }
                break;
            case 3:
                for (int d = 0; d < 4; d++) {
                    for (int i = 0; i < N; i++) {
                        System.arraycopy(prevMap[i], 0, isVisited[i], 0, M);
                    }

                    detect(isVisited, x, y, d);
                    detect(isVisited, x, y, (d + 1) % 4);
                    check(cnt + 1, isVisited);
                }
                break;
            case 4:
                for (int d = 0; d < 4; d++) {
                    for (int i = 0; i < N; i++) {
                        System.arraycopy(prevMap[i], 0, isVisited[i], 0, M);
                    }

                    detect(isVisited, x, y, d);
                    detect(isVisited, x, y, (d + 1) % 4);
                    detect(isVisited, x, y, (d + 2) % 4);
                    check(cnt + 1, isVisited);
                }
                break;
            case 5:
                for (int i = 0; i < N; i++) {
                    System.arraycopy(prevMap[i], 0, isVisited[i], 0, M);
                }

                detect(isVisited, x, y, 0);
                detect(isVisited, x, y, 1);
                detect(isVisited, x, y, 2);
                detect(isVisited, x, y, 3);
                check(cnt + 1, isVisited);

                break;
        }

    }

    private static void detect(int[][] isVisited, int x, int y, int dir) {
        switch (dir) {
            case 0:
                for (int i = x; i >= 0; i--) {
                    if(map[i][y] == WALL) break;
                    isVisited[i][y] = OK;
                }
                break;
            case 1:
                for (int j = y; j < M; j++) {
                    if(map[x][j] == WALL) break;
                    isVisited[x][j] = OK;
                }
                break;
            case 2:
                for (int i = x; i < N; i++) {
                    if(map[i][y] == WALL) break;
                    isVisited[i][y] = OK;
                }
                break;
            case 3:
                for (int j = y; j >= 0; j--) {
                    if(map[x][j] == WALL) break;
                    isVisited[x][j] = OK;
                }
                break;
        }
    }
}
