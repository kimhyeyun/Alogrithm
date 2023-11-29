import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_4485_녹색_옷_입은_애가_젤다지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int index = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem " + index++ + ": " + findMinRupee(cave, N)).append("\n");
        }
        System.out.println(sb);

    }

    private static int findMinRupee(int[][] cave, int N) {
        final int[] dx = {-1, 0, 0, 1};
        final int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        int[][] rupee = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(rupee[i], Integer.MAX_VALUE);
        }

        rupee[0][0] = cave[0][0];
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(rupee[nx][ny] <= rupee[cur[0]][cur[1]] + cave[nx][ny]) continue;

                rupee[nx][ny] = rupee[cur[0]][cur[1]] + cave[nx][ny];
                q.add(new int[]{nx, ny});
            }
        }
        return rupee[N - 1][N - 1];
    }

}
