import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_4485_녹색_옷_입은_애가_젤다지 {
    static int N;
    static int[][] cave;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int idx = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                return;
            }

            cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            System.out.println("Problem " + idx++ +": " + findMinRupee());
        }
    }

    private static int findMinRupee() {
        Queue<int[]> q = new LinkedList<>();
        int[][] rupee = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(rupee[i], Integer.MAX_VALUE);
        }

        rupee[0][0] = cave[0][0];
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                if (rupee[nx][ny] <= rupee[now[0]][now[1]] + cave[nx][ny]) {
                    continue;
                }

                rupee[nx][ny] = rupee[now[0]][now[1]] + cave[nx][ny];
                q.add(new int[]{nx, ny});
            }
        }
        return rupee[N - 1][N - 1];
    }
}
