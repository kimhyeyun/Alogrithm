import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_21609_상어_중학교 {
    static class Group{
        int countOfTotal, countOfRainBow;
        int xOfReference, yOfReference;

        public Group(int countOfTotal, int countOfRainBow, int xOfReference, int yOfReference) {
            this.countOfTotal = countOfTotal;
            this.countOfRainBow = countOfRainBow;
            this.xOfReference = xOfReference;
            this.yOfReference = yOfReference;
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][] isVisted;
    static PriorityQueue<Group> groups = new PriorityQueue<>((o1, o2) -> {
            if (o1.countOfTotal == o2.countOfTotal) {
                if (o1.countOfRainBow == o2.countOfRainBow) {
                    if (o1.xOfReference == o2.xOfReference) {
                        return o2.yOfReference - o1.yOfReference;
                    }
                    return o2.xOfReference - o1.xOfReference;
                }
                return o2.countOfRainBow - o1.countOfRainBow;
            }
            return o2.countOfTotal - o1.countOfTotal;
        }
    );

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    static final int BLANK = -9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            groups.clear();
            isVisted = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (0 < map[i][j] && map[i][j] <= M && !isVisted[i][j]) {
                        findBlockGroup(i, j, true);
                    }
                }
            }

            if(groups.isEmpty()) break;

            isVisted = new boolean[N][N];
            Group g = groups.peek();
            findBlockGroup(g.xOfReference, g.yOfReference, false);

            answer += removeBlock();

            gravity();
            map = rotate();
            gravity();
        }

        System.out.println(answer);
    }

    private static int[][] rotate() {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[j][N - i - 1];
            }
        }
        return tmp;
    }

    private static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if(map[i][j] == -1 || map[i][j] == BLANK) continue;
                int dest = i + 1;

                while (true) {
                    if(dest == N) break;
                    if(map[dest][j] == BLANK) dest += 1;
                    else break;
                }

                if (dest == i + 1) continue;
                map[dest - 1][j] = map[i][j];
                map[i][j] = BLANK;
            }
        }
    }

    private static int removeBlock() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisted[i][j]) {
                    count += 1;
                    map[i][j] = BLANK;
                }
            }
        }
        return (int) Math.pow(count, 2);
    }

    private static void findBlockGroup(int x, int y, boolean isCheckedRainbow) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        isVisted[x][y] = true;

        int count = 1, rainbow = 0;
        int colorOfBlock = map[x][y];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisted[nx][ny]) continue;
                if(map[nx][ny] != 0 && map[nx][ny] != colorOfBlock) continue;

                if(map[nx][ny] == 0) rainbow += 1;

                count += 1;
                isVisted[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        if (count >= 2) groups.add(new Group(count, rainbow, x, y));
        if (isCheckedRainbow) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 0) isVisted[i][j] = false;
                }
            }
        }
    }
}
