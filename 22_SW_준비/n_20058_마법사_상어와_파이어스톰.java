import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_20058_마법사_상어와_파이어스톰 {
    static int N, Q, answer, totalOfIce;
    static int[] L;
    static int[][] iceSheet;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);
        iceSheet = new int[N][N];
        L = new int[Q];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                iceSheet[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            iceSheet = divide(L[i]);
            iceSheet = meltIce();
        }

        findLargestIceberg();
        System.out.println(totalOfIce);
        System.out.println(answer);
    }

    private static void findLargestIceberg() {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        answer = 0;
        totalOfIce = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if(iceSheet[x][y] <= 0 || isVisited[x][y]) continue;

                q.add(new int[]{x, y});
                isVisited[x][y] = true;
                totalOfIce += iceSheet[x][y];

                int cnt = 1;
                while (!q.isEmpty()) {
                    int[] now = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                        if(isVisited[nx][ny] || iceSheet[nx][ny] <= 0) continue;

                        cnt += 1;

                        isVisited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                        totalOfIce += iceSheet[nx][ny];
                    }
                }

                answer = Math.max(answer, cnt);
            }
        }

    }

    private static int[][] meltIce() {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            tmp[i] = Arrays.copyOf(iceSheet[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                if(iceSheet[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                    if(iceSheet[nx][ny] <= 0) continue;

                    cnt += 1;
                }

                if(cnt < 3) tmp[i][j] -= 1;
            }
        }
        return tmp;
    }

    private static int[][] divide(int size) {
        int[][] tmp = new int[N][N];
        size = (int) Math.pow(2, size);

        for (int i = 0; i < N; i += size) {
            for (int j = 0; j < N; j += size) {
                rotate(i, j, size, tmp);
            }
        }
        return tmp;
    }

    private static void rotate(int x, int y, int size, int[][] tmp) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[j + x][y + size - i - 1] = iceSheet[i + x][j + y];
            }
        }
    }
}
