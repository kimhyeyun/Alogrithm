import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_20056_마법사_상어와_파이어볼 {
    static class FireBall {
        int x, y;
        int mass, dir, speed;

        public FireBall(int x, int y, int mass, int dir, int speed) {
            this.x = x;
            this.y = y;
            this.mass = mass;
            this.dir = dir;
            this.speed = speed;
        }
    }
    static int N, M, K;
    static List<FireBall>[][] fireBallsInMap;
    static List<FireBall> fireBalls;

    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBallsInMap = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fireBallsInMap[i][j] = new LinkedList<>();
            }
        }

        fireBalls = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBalls.add(new FireBall(x, y, m, d, s));
        }

        while (K-- > 0) {
            moveFireBalls();
        }

        int answer = 0;
        for (FireBall f : fireBalls) {
            answer += f.mass;
        }

        System.out.println(answer);
    }

    private static void moveFireBalls() {
        for (FireBall now : fireBalls) {
            int nx = now.x;
            int ny = now.y;

            for (int i = 0; i < now.speed; i++) {
                nx += dx[now.dir];
                ny += dy[now.dir];

                if(nx == -1) nx = N - 1;
                if(ny == -1) ny = N - 1;
                if(nx == N) nx = 0;
                if(ny == N) ny = 0;
            }

            now.x = nx;
            now.y = ny;
            fireBallsInMap[nx][ny].add(now);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(fireBallsInMap[i][j].size() < 2){
                    fireBallsInMap[i][j].clear();
                    continue;
                }

                int sumMass = 0, sumSpeed = 0;
                boolean isOdd = false, isEven = false;

                for (FireBall f : fireBallsInMap[i][j]) {
                    sumMass += f.mass;
                    sumSpeed += f.speed;

                    if(f.dir % 2 == 0) isEven = true;
                    else isOdd = true;

                    fireBalls.remove(f);
                }

                int m = Math.floorDiv(sumMass, 5);
                int s = Math.floorDiv(sumSpeed, fireBallsInMap[i][j].size());

                fireBallsInMap[i][j].clear();

                if(m <= 0) continue;
                int d;

                if(isEven && isOdd) d = 1;
                else d = 0;

                for (int f = 0; f < 4; f++, d += 2) {
                    fireBalls.add(new FireBall(i, j, m, d, s));
                }
            }
        }
    }
}
