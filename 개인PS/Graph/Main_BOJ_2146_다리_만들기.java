package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2146_다리_만들기 {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int idx = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    makeIslandOfNum(i, j, idx);
                    idx -= 1;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 0) {
                    isVisited = new boolean[N][N];
                    int res = makeBridge(i, j);
                    if(res == -1) continue;

                    ans = ans > res ? res : ans;
                }
            }
        }

        System.out.println(ans - 1);

    }

    private static int makeBridge(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        int numOfIsland = map[x][y];
        isVisited[x][y] = true;
        q.offer(new int[]{x, y, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cntOfBridge = now[2];

            if(map[now[0]][now[1]] != 0 && map[now[0]][now[1]] != numOfIsland) return cntOfBridge;

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(map[nx][ny] == numOfIsland || isVisited[nx][ny]) continue;

                q.offer(new int[]{nx, ny, cntOfBridge + 1});
                isVisited[nx][ny] = true;
            }
        }
        return -1;
    }

    private static void makeIslandOfNum(int x, int y, int idx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        map[x][y] = idx;
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] != 1 || isVisited[nx][ny]) continue;

                map[nx][ny] = idx;
                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}
