import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_포탑_부수기 {
    static class Tower implements Comparable<Tower> {
        int x, y, r, p;

        public Tower(int x, int y, int r, int p) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.p = p;
        }

        @Override
        public int compareTo(Tower o) {
            if (this.p != o.p) return this.p - o.p;
            if (this.r != o.r) return o.r - this.r;
            if (this.x + this.y != o.x + o.y) return (o.x + o.y) - (this.x + this.y);
            return o.y - this.y;
        }
    } 

    static int N, M, K, turn;
    static int[][] powers, rec;
    static boolean[][] isVisited, isActived;
    static int[][] backX, backY;
    static List<Tower> liveTowers;

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx2 = {0, 0, 0, -1, -1, -1, 1, 1, 1};
    static final int[] dy2 = {0, -1, 1, 0, -1, 1, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        powers = new int[N][M];
        rec = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                powers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (K-- > 0) {
            liveTowers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (powers[i][j] > 0) {
                        liveTowers.add(new Tower(i, j, rec[i][j], powers[i][j]));
                    }
                }
            }
            
            if (liveTowers.size() <= 1) break;

            turn += 1;
            isActived = new boolean[N][M];
            isVisited = new boolean[N][M];
            backX = new int[N][M];
            backY = new int[N][M];

            awake();
            if (!laserAttack()) bombAttack();
            reserve();
        }


        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, powers[i][j]);
            }
        }
        System.out.println(answer);
    }

    private static void reserve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isActived[i][j] || powers[i][j] == 0) continue;
                powers[i][j] += 1;
            }
        }
    }

    private static void bombAttack() {
        Tower weakTower = liveTowers.get(0);
        int sx = weakTower.x;
        int sy = weakTower.y;
        int power = weakTower.p;

        Tower strongTower = liveTowers.get(liveTowers.size() - 1);
        int ex = strongTower.x;
        int ey = strongTower.y;

        for (int d = 0; d < 9; d++) {
            int nx = (ex + dx2[d] + N) % N;
            int ny = (ey + dy2[d] + M) % M;

            if (nx == sx && ny == sy) continue;
            if (nx == ex && ny == ey) {
                powers[nx][ny] = Math.max(powers[nx][ny] - power, 0);
                isActived[nx][ny] = true;
            } else {
                powers[nx][ny] = Math.max(powers[nx][ny] - (power / 2), 0);
                isActived[nx][ny] = true;
            }
        }
    }

    private static boolean laserAttack() {
        Tower weakTower = liveTowers.get(0);
        int sx = weakTower.x;
        int sy = weakTower.y;
        int power = weakTower.p;

        Tower strongTower = liveTowers.get(liveTowers.size() - 1);
        int ex = strongTower.x;
        int ey = strongTower.y;

        Queue<int[]> q = new LinkedList<>();
        isVisited[sx][sy] = true;
        q.add(new int[]{sx, sy});

        boolean canAttack = false;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            if (x == ex && y == ey) {
                canAttack = true;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = (x + dx[d] + N) % N;
                int ny = (y + dy[d] + M) % M;

                if (isVisited[nx][ny] || powers[nx][ny] == 0) continue;

                isVisited[nx][ny] = true;
                backX[nx][ny] = x;
                backY[nx][ny] = y;
                q.add(new int[]{nx, ny});
            }
        }

        if (canAttack) {
            powers[ex][ey] = Math.max(powers[ex][ey] - power, 0);
            isActived[ex][ey] = true;

            int cx = backX[ex][ey];
            int cy = backY[ex][ey];

            while (!(cx == sx && cy == sy)) {
                powers[cx][cy] = Math.max(powers[cx][cy] - power / 2, 0);
                isActived[cx][cy] = true;

                int ncx = backX[cx][cy];
                int ncy = backY[cx][cy];

                cx = ncx;
                cy = ncy;
            }
        }
        return canAttack;
    }

    private static void awake() {
        Collections.sort(liveTowers);

        Tower weakTower = liveTowers.get(0);
        int x = weakTower.x;
        int y = weakTower.y;

        powers[x][y] += N + M;
        rec[x][y] = turn;
        weakTower.p = powers[x][y];
        isActived[x][y] = true;

        liveTowers.set(0, weakTower);
    }

}
