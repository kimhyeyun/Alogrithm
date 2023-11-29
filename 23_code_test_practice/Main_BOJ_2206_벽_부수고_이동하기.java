import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2206_벽_부수고_이동하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(BFS(map, N, M));
    }

    private static int BFS(char[][] map, int N, int M) {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        boolean[][][] isVisited = new boolean[N][M][2];

        q.add(new int[]{0, 0, 1, 0});
        isVisited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == N - 1 && cur[1] == M - 1) return cur[2];

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

                if (map[nx][ny] == '0') {
                    if (cur[3] == 0 && !isVisited[nx][ny][0]) {
                        q.add(new int[]{nx, ny, cur[2] + 1, 0});
                        isVisited[nx][ny][0] = true;
                    } else if (cur[3] == 1 && !isVisited[nx][ny][1]) {
                        q.add(new int[]{nx, ny, cur[2] + 1, 1});
                        isVisited[nx][ny][1] = true;
                    }
                } else {
                    if (cur[3] == 0) {
                        q.add(new int[]{nx, ny, cur[2] + 1, 1});
                        isVisited[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}
