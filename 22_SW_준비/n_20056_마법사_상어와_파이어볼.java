import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class n_20056_마법사_상어와_파이어볼 {
    static class fireBall {
        int x, y, m, s, d;

        public fireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N, M, K;
    static List<fireBall> fireBalls;
    static List<fireBall>[][] fireBallsMap;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBallsMap = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fireBallsMap[i][j] = new ArrayList<>();
            }
        }
        fireBalls = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBalls.add(new fireBall(x, y, m, s, d));
        }

        while (K-- > 0) {
            moveFireBalls();
        }

        int answer = 0;
        for (fireBall f : fireBalls) {
            answer += f.m;
        }

        System.out.println(answer);
    }

    private static void moveFireBalls() {
        for (fireBall now : fireBalls) {
            int nx = now.x, ny = now.y;
            for (int i = 0; i < now.s; i++) {
                nx += dx[now.d];
                ny += dy[now.d];

                if(nx == -1) nx = N - 1;
                if(ny == -1) ny = N - 1;
                if(nx == N) nx = 0;
                if(ny == N) ny = 0;
            }

            now.x = nx;
            now.y = ny;
            fireBallsMap[nx][ny].add(now);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(fireBallsMap[i][j].size() < 2){
                    fireBallsMap[i][j].clear();
                    continue;
                }


                int sumMass = 0, sumSpeed = 0;
                boolean isOdd = false, isEven = false;
                for (fireBall f : fireBallsMap[i][j]) {
                    sumMass += f.m;
                    sumSpeed += f.s;
                    if(f.d % 2 == 0) isEven = true;
                    else isOdd = true;

                    fireBalls.remove(f);
                }

                int mass = Math.floorDiv(sumMass, 5);
                int speed = Math.floorDiv(sumSpeed, fireBallsMap[i][j].size());

                fireBallsMap[i][j].clear();

                if(mass <= 0) continue;
                int dir;
                if (isEven && isOdd) {
                    dir = 1;
                } else {
                    dir = 0;
                }
                for (int f = 0; f < 4; f++, dir+=2) {
                    fireBalls.add(new fireBall(i, j, mass, speed, dir));
                }
            }
        }
    }
}
