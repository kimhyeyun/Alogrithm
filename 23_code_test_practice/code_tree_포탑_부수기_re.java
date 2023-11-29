import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_포탑_부수기_re {
    public static class Turret implements Comparable<Turret> {
        int x, y;
        int power, timeOfAttack;

        public Turret(int x, int y, int power, int timeOfAttack) {
            this.x = x;
            this.y = y;
            this.power = power;
            this.timeOfAttack = timeOfAttack;
        }

        @Override
        public int compareTo(Turret o) {
            if (this.power != o.power) return this.power - o.power;
            if (this.timeOfAttack != o.timeOfAttack) return o.timeOfAttack - this.timeOfAttack;
            if ((this.x + this.y) != (o.x + o.y)) return (o.x + o.y) - (this.x + this.y);
            return o.y - this.y;
        }
    }
    public static int n, m, k, turn;
    public static int[][] powerOfTurrets, timeOfAttackOfTurrets;
    public static boolean[][] isActivated, isVisited;
    public static int[][] backX, backY;
    public static List<Turret> turrets;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};
    public static final int[] dx2 = {0, 0, 0, 1, 1, 1, -1, -1, -1};
    public static final int[] dy2 = {0, -1, 1, 0, -1, 1, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        init();

        while (k-- > 0) {
            turrets = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (powerOfTurrets[i][j] > 0) {
                        turrets.add(new Turret(i, j, powerOfTurrets[i][j], timeOfAttackOfTurrets[i][j]));
                    }
                }
            }

            if (turrets.size() == 1) break;

            turn += 1;
            isActivated = new boolean[n][m];
            isVisited = new boolean[n][m];

            backX = new int[n][m];
            backY = new int[n][m];

            awakeTurret();
            if (!laserAttack()) bombAttack();
            maintainTurrets();
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = Math.max(answer, powerOfTurrets[i][j]);
            }
        }
        System.out.println(answer);
    }

    private static void maintainTurrets() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isActivated[i][j] || powerOfTurrets[i][j] == 0) continue;
                powerOfTurrets[i][j] += 1;
            }
        }
    }

    private static void bombAttack() {
        Turret attacker = turrets.get(0);
        Turret attackedTurret = turrets.get(turrets.size() - 1);
        int ex = attackedTurret.x;
        int ey = attackedTurret.y;

        for (int d = 0; d < 9; d++) {
            int nx = (ex + dx2[d] + n) % n;
            int ny = (ey + dy2[d] + m) % m;

            if (nx == attacker.x && ny == attacker.y) continue;
            if (nx == ex && ny == ey) {
                powerOfTurrets[ex][ey] = Math.max(powerOfTurrets[ex][ey] - attacker.power, 0);
                isActivated[ex][ey] = true;
            } else {
                powerOfTurrets[nx][ny] = Math.max(powerOfTurrets[nx][ny] - (attacker.power / 2), 0);
                isActivated[nx][ny] = true;
            }
        }
    }

    private static boolean laserAttack() {
        Turret attacker = turrets.get(0);
        int sx = attacker.x;
        int sy = attacker.y;
        int power = attacker.power;

        Turret attackedTurret = turrets.get(turrets.size() - 1);
        int ex = attackedTurret.x;
        int ey = attackedTurret.y;

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
                int nx = (x + dx[d] + n) % n;
                int ny = (y + dy[d] + m) % m;

                if (isVisited[nx][ny] || powerOfTurrets[nx][ny] == 0) continue;

                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                backX[nx][ny] = x;
                backY[nx][ny] = y;
            }
        }

        if (canAttack) {
            powerOfTurrets[ex][ey] = Math.max(powerOfTurrets[ex][ey] - power, 0);
            isActivated[ex][ey] = true;

            int nx = backX[ex][ey];
            int ny = backY[ex][ey];

            while (!(nx == sx && ny == sy)) {
                powerOfTurrets[nx][ny] = Math.max(powerOfTurrets[nx][ny] - power / 2, 0);
                isActivated[nx][ny] = true;

                int nnx = backX[nx][ny];
                int nny = backY[nx][ny];

                nx = nnx;
                ny = nny;
            }
        }
        return canAttack;
    }

    private static void awakeTurret() {
        Collections.sort(turrets);

        Turret attacker = turrets.get(0);
        int x = attacker.x;
        int y = attacker.y;

        powerOfTurrets[x][y] += (n + m);
        timeOfAttackOfTurrets[x][y] = turn;
        attacker.power = powerOfTurrets[x][y];
        isActivated[x][y] = true;

        turrets.add(0, attacker);
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        powerOfTurrets = new int[n][m];
        timeOfAttackOfTurrets = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                powerOfTurrets[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
