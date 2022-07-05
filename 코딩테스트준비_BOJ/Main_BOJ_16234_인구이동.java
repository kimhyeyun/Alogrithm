import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16234_인구이동 {
    static int N, L, R;
    static int[][] countries;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Queue<int[]> queue;
    static List<int[]> union;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);

    }

    private static int solution() {
        int cnt = 0;
        boolean isMove;

        while (true) {
            isVisited = new boolean[N][N];
            isMove = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(isVisited[i][j]) continue;
                    if(isOpen(i,j)) isMove = true;
                }
            }

            if(isMove) cnt++;
            else return cnt;
        }
    }

    private static boolean isOpen(int i, int j) {
        queue = new LinkedList<>();
        union = new ArrayList<>();

        queue.add(new int[]{i, j});
        union.add(new int[]{i, j});
        isVisited[i][j] = true;

        int population = countries[i][j];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || N <= nx || ny < 0 || N <= ny || isVisited[nx][ny]) continue;
                int dif = Math.abs(countries[now[0]][now[1]] - countries[nx][ny]);
                if(dif < L || R < dif) continue;

                population += countries[nx][ny];
                queue.add(new int[]{nx, ny});
                union.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
            }
        }

        if(union.size() < 2) return false;

        int tmp = population / union.size();
        for (int[] u : union) {
            countries[u[0]][u[1]] = tmp;
        }
        return true;


    }
}
