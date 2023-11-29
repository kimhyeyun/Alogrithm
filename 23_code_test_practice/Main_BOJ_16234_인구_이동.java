import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16234_인구_이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] populations = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean[][] isVisited;
        boolean isMoved;

        while (true) {
            isVisited = new boolean[N][N];
            isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(isVisited[i][j]) continue;
                    if (isOpened(i, j, N, L, R, isVisited, populations)) isMoved = true;
                }
            }

            if(!isMoved) break;
            answer += 1;
        }
        System.out.println(answer);
    }

    private static boolean isOpened(int x, int y, int N, int L, int R, boolean[][] isVisited, int[][] populations) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        List<int[]> unions = new LinkedList<>();

        q.add(new int[]{x, y});
        unions.add(new int[]{x, y});
        isVisited[x][y] = true;

        int sum = populations[x][y];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny]) continue;

                int diff = Math.abs(populations[cur[0]][cur[1]] - populations[nx][ny]);
                if(diff < L || R < diff) continue;

                q.add(new int[]{nx, ny});
                unions.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;

                sum += populations[nx][ny];
            }
        }

        if (unions.size() >= 2) {
            int p = sum / unions.size();

            for (int[] u : unions) {
                populations[u[0]][u[1]] = p;
            }

            return true;
        }
        return false;
    }
}
