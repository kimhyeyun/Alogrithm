import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17484_진우의_달_여행_Small {
    static int N, M;
    static int[][] fuels;
    static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fuels = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                fuels[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            answer = Math.min(answer, calFuel(0, i, -1));
        }

        System.out.println(answer);
    }

    private static int calFuel(int x, int y, int dir) {
        if (x == N) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        for (int d = 0; d < 3; d++) {
            if (dir == d) continue;
            if(y + dy[d] < 0 || M <= y + dy[d]) continue;

            result = Math.min(result, calFuel(x + 1, y + dy[d], d) + fuels[x][y]);
        }

        return result;
    }
}
