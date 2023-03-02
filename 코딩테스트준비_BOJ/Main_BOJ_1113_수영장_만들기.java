import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1113_수영장_만들기 {
    static int N, M;
    static int maxHeight, answer;
    static int[][] pool;
    static boolean[][] isVisited;
    static Queue<int[]> q;
    static boolean checked;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maxHeight = Integer.MIN_VALUE;
        answer = 0;

        q = new LinkedList<>();

        pool = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                pool[i][j] = tmp[j] - '0';
                maxHeight = maxHeight < pool[i][j] ? pool[i][j] : maxHeight;
            }
        }

        for (int h = 2; h <= maxHeight; h++) {
            isVisited = new boolean[N][M];
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    checked = false;
                    if (pool[i][j] < h && !isVisited[i][j]) {
                        isVisited[i][j] = true;
                        q.add(new int[]{i, j});
                        answer += solve(h);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static int solve(int height) {
        int size = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) {
                    checked = true;
                    continue;
                } else if (!isVisited[nx][ny] && pool[nx][ny] < height) {
                    isVisited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    size += 1;
                }
            }
        }
        if(checked) size = 0;
        return size;
    }
}
