import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20056_마법사_상어와_파이어볼_re {
    static class FireBall {
        int x, y, m, s, d;

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    static List<FireBall>[][] map;
    static List<FireBall> fireBalls;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        fireBalls = new ArrayList<>();
        map = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            fireBalls.add(new FireBall(x, y, m, s, d));
        }

        while (K-- > 0) {
            moveFireBalls();
            checkFireBalls();
        }

        int ans = 0;
        for (FireBall fireBall : fireBalls) {
            ans += fireBall.m;
        }

        System.out.println(ans);
    }

    private static void checkFireBalls() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size() == 1) map[i][j].clear();
                if(map[i][j].size() < 2) continue;

                boolean even = map[i][j].get(0).d % 2 == 0 ? true : false;
                boolean odd = map[i][j].get(0).d % 2 == 1 ? true : false;

                int mSum = 0, sSum = 0;
                for (FireBall fireBall : map[i][j]) {
                    mSum += fireBall.m;
                    sSum += fireBall.s;

                    even = even & fireBall.d % 2 == 0 ? true : false;
                    odd = odd & fireBall.d % 2 == 1 ? true : false;

                    fireBalls.remove(fireBall);
                }

                int divMass = Math.floorDiv(mSum, 5);
                int divSpeed = Math.floorDiv(sSum, map[i][j].size());

                map[i][j].clear();

                if(divMass == 0) continue;
                if (even || odd) {
                    for (int k = 0; k < 7; k += 2) {
                        fireBalls.add(new FireBall(i, j, divMass, divSpeed, k));
                    }
                } else {
                    for (int k = 1; k < 8; k += 2) {
                        fireBalls.add(new FireBall(i, j, divMass, divSpeed, k));
                    }
                }
            }
        }
    }

    private static void moveFireBalls() {
        for (FireBall fireBall : fireBalls) {
            int nx = fireBall.x;
            int ny = fireBall.y;
            for (int i = 0; i < fireBall.s; i++) {
                nx += dx[fireBall.d];
                ny += dy[fireBall.d];

                if(nx == N) nx = 0;
                if(nx == -1) nx = N - 1;
                if(ny == N) ny = 0;
                if(ny == -1) ny = N - 1;
            }

            fireBall.x = nx;
            fireBall.y = ny;

            map[nx][ny].add(fireBall);
        }
    }
}
