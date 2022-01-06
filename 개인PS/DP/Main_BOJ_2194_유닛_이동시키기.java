package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2194_유닛_이동시키기 {
    static int N, M, A, B, K;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static unit startPoint, endPoint;

    public static class unit {
        int x;
        int y;

        public unit(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        A = Integer.parseInt(stringTokenizer.nextToken());
        B = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            map[x][y] = -1;
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        startPoint = new unit(x, y);

        stringTokenizer = new StringTokenizer(br.readLine());
        x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        endPoint = new unit(x, y);

        int ans = BFS();

        System.out.println(ans);

    }

    private static int BFS() {
        Queue<unit> queue = new LinkedList<>();
        queue.offer(startPoint);
        isVisited[startPoint.x][startPoint.y] = true;
        int result = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                unit now = queue.poll();
//                System.out.println("now: " + now.x + " " + now.y);
                if (endPoint.x == now.x && endPoint.y == now.y)
                    return result;
                for (int d = 0; d < 4; d++) {
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];
//                System.out.println("nx : " + nx + " ny: " + ny);

                    if (!isRange(nx, ny)) continue;
                    if (!isPossible(nx, ny)) continue;

                    queue.offer(new unit(nx, ny));
//                    System.out.println("next : " + nx + " " + ny);
                }
            }
            result++;
        }
        return -1;
    }

    private static boolean isPossible(int x, int y) {
        if (isVisited[x][y])
            return false;
        for (int i = x; i < x + A; i++) {
            for (int j = y; j < y + B; j++) {
                if (!isRange(i, j))
                    return false;
                if (map[i][j] == -1)
                    return false;
            }
        }
        isVisited[x][y] = true;
        return true;
    }

    private static boolean isRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M)
            return true;
        return false;
    }
}
