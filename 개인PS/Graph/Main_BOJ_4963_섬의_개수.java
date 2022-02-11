package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_4963_섬의_개수 {
    static int w, h;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        while (true) {
            stringTokenizer = new StringTokenizer(br.readLine());
            w = Integer.parseInt(stringTokenizer.nextToken());
            h = Integer.parseInt(stringTokenizer.nextToken());

            if(w == 0 && h == 0) break;

//            System.out.println("w : " + w + " h : " + h);
            map = new int[h][w];
            isVisited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int cnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!isVisited[i][j] && map[i][j] == 1) {
                        cnt += 1;
                        BFS(i, j);
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 8; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || h <= nx || w <= ny || isVisited[nx][ny] || map[nx][ny] == 0) continue;

                q.offer(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }
    }
}
