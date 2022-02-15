import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_21609_상어_중학교 {

    static class Block implements Comparable<Block>{
        int numOfTotal, numOfRainBow, x, y;

        public Block(int numOfTotal, int numOfRainBow, int x, int y) {
            this.numOfTotal = numOfTotal;
            this.numOfRainBow = numOfRainBow;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Block o) {
            if (this.numOfTotal == o.numOfTotal) {
                if (this.numOfRainBow == o.numOfRainBow) {
                    if(this.x == o.x) return o.y - this.y;
                    return o.x - this.x;
                }
                return o.numOfRainBow - this.numOfRainBow;
            }
            return o.numOfTotal - this.numOfTotal;
        }
    }

    static int N, M, ans = 0;
    static final int BLACK = 6;
    static int[][] gameMap;
    static boolean[][] isVisited;
    static List<Block> blockGroup;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        gameMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                gameMap[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (true) {
            blockGroup = new ArrayList<>();
            isVisited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(0 < gameMap[i][j] && gameMap[i][j] <= M && !isVisited[i][j]) findBlockGroup(i, j, true);
                }
            }
            if(blockGroup.isEmpty()) break;

            Collections.sort(blockGroup);
            isVisited = new boolean[N][N];
            findBlockGroup(blockGroup.get(0).x, blockGroup.get(0).y, false);
            removeBlock();
            ans += blockGroup.get(0).numOfTotal * blockGroup.get(0).numOfTotal;
            gravity();
            rotate();
            gravity();
        }

        System.out.println(ans);
    }

    private static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = gameMap[j][N - i - 1];
            }
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(tmp[i], 0, gameMap[i], 0, N);
        }
    }

    private static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if(gameMap[i][j] == BLACK || gameMap[i][j] == -1) continue;
                int dest = i + 1;

                while (true) {
                    if(dest == N) break;
                    if(gameMap[dest][j] == BLACK) dest += 1;
                    else break;
                }
                if(dest == i+1) continue;
                gameMap[dest - 1][j] = gameMap[i][j];
                gameMap[i][j] = BLACK;
            }
        }
    }

    private static void removeBlock() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(isVisited[i][j]){
                    cnt += 1;
                    gameMap[i][j] = BLACK;
                }
            }
        }
    }

    private static void findBlockGroup(int x, int y, boolean flag) {
        Queue<int[]> q = new LinkedList<>();
        int blockColor = gameMap[x][y];
        isVisited[x][y] = true;
        q.add(new int[]{x, y});

        int total = 1;
        int rainbow = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny]) continue;
                if (gameMap[nx][ny] != 0 && gameMap[nx][ny] != blockColor) continue;

                if (gameMap[nx][ny] == 0) rainbow += 1;
                total += 1;
                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        if (total >= 2) blockGroup.add(new Block(total, rainbow, x, y));
        if (flag) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (gameMap[i][j] == 0) isVisited[i][j] = false;
                }
            }
        }
    }
}
