import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n_16234_인구_이동 {
    static int N, L, R;
    static int[][] populations;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
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
            boolean isMove = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(isVisited[i][j]) continue;
                    if(isOpen(i, j)) isMove = true;
                }
            }

            if(isMove) answer += 1;
            else break;
        }

        System.out.println(answer);
    }

    private static boolean isOpen(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        q.add(new int[]{x, y});
        isVisited[x][y] = true;
        union.add(new int[]{x, y});

        int sum = populations[x][y];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(isVisited[nx][ny]) continue;

                int diff = Math.abs(populations[nx][ny] - populations[now[0]][now[1]]);
                if(diff < L || R < diff) continue;

                sum += populations[nx][ny];
                q.add(new int[]{nx, ny});
                union.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;

            }
        }

        if(union.size() < 2) return false;
        int tmp = sum / union.size();
        for (int[] u : union) {
            populations[u[0]][u[1]] = tmp;
        }
        return true;
    }
}
