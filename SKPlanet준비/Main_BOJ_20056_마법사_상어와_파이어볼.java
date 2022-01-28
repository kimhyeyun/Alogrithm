import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20056_마법사_상어와_파이어볼 {

    static int N, M, K;
    static List<FireBall> map[][];
    static List<FireBall> fireBalls;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall {
        int row;
        int col;
        int mass;
        int speed;
        int direction;

        public FireBall(int row, int col, int mass, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        fireBalls = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            fireBalls.add(new FireBall(r, c, m, s, d));
        }

        while (K-- > 0) {
            moveFireBall();
            checkFireBall();
        }

        int ans = 0;
        for (FireBall f : fireBalls) {
            ans += f.mass;
        }
        System.out.println(ans);
    }

    private static void checkFireBall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size() == 1)   map[i][j].clear();
                if(map[i][j].size() < 2) continue;

                int massSum = 0;
                int speedSum = 0;

                boolean even = map[i][j].get(0).direction % 2 == 0 ? true : false;
                boolean odd = map[i][j].get(0).direction % 2 == 1 ? true : false;

                for (FireBall now : map[i][j]) {
                    massSum += now.mass;
                    speedSum += now.speed;
                    even = even & now.direction % 2 == 0 ? true : false;
                    odd = odd & now.direction % 2 == 1 ? true : false;

                    fireBalls.remove(now);
                }

                int divMass = Math.floorDiv(massSum, 5);
                int divSpeed = Math.floorDiv(speedSum, map[i][j].size());

                map[i][j].clear();

                if(divMass == 0) continue;
                if (even || odd) {
                    for (int k = 0; k < 8; k += 2) {
                        fireBalls.add(new FireBall(i, j, divMass, divSpeed, k));
                    }
                }else{
                    for (int k = 1; k < 8; k += 2) {
                        fireBalls.add(new FireBall(i, j, divMass, divSpeed, k));
                    }
                }
            }
        }
    }

    private static void moveFireBall() {
        for (FireBall f : fireBalls) {
            int nr = (f.row + N + dr[f.direction] * f.speed % N) % N;
            int nc = (f.col + N + dc[f.direction] * f.speed % N) % N;

            f.row = nr;
            f.col = nc;

            map[nr][nc].add(f);
        }
    }
}
