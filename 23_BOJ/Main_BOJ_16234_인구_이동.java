import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16234_인구_이동 {
    static int N, L, R;
    static int[][] populations;
    static boolean[][] isVisited;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        populations = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            isVisited = new boolean[N][N];
            boolean isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isVisited[i][j]) continue;
                    if (isOpen(i, j)) isMoved = true;
                }
            }

            if(!isMoved) break;
            answer += 1;
        }
        System.out.println(answer);
    }

    private static boolean isOpen(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> unions = new LinkedList<>();

        q.add(new int[]{x, y});
        unions.add(new int[]{x, y});
        isVisited[x][y] = true;

        int sum = populations[x][y];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny]) continue;

                int diff = Math.abs(populations[now[0]][now[1]] - populations[nx][ny]);
                if (L <= diff && diff <= R) {
                    q.add(new int[]{nx, ny});
                    unions.add(new int[]{nx, ny});
                    isVisited[nx][ny] = true;

                    sum += populations[nx][ny];
                }
            }
        }

        if (unions.size() >= 2) {
            int p = sum / unions.size();

            for (int[] nation : unions) {
                populations[nation[0]][nation[1]] = p;
            }
            return true;
        }
        return false;
    }
}
