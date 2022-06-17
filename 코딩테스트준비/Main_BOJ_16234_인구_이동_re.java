import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16234_인구_이동_re {
    static int N, L, R;
    static int[][] population;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        population = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            isVisited = new boolean[N][N];
            boolean isMove = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(isVisited[i][j]) continue;
                    if(isOpen(i, j)) isMove = true;
                }
            }
            if (isMove) {
                ans += 1;
            } else {
                System.out.println(ans);
                return;
            }
        }
    }

    private static boolean isOpen(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        q.add(new int[]{x, y});
        union.add(new int[]{x, y});
        isVisited[x][y] = true;

        int sum = population[x][y];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(isVisited[nx][ny]) continue;

                int diff = Math.abs(population[now[0]][now[1]] - population[nx][ny]);

                if(diff < L || R < diff) continue;

                sum += population[nx][ny];

                q.add(new int[]{nx, ny});
                union.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }

        if(union.size() < 2) return false;

        int tmp = sum / union.size();
        for (int[] u : union) {
            population[u[0]][u[1]] = tmp;
        }
        return true;
    }
}
