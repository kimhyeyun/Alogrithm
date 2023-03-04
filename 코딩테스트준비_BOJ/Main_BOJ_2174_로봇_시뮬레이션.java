import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2174_로봇_시뮬레이션 {
    static class Robot{
        int x, y;
        int dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int A, B, N, M;
    static int[][] space;
    static Robot[] robots;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        space = new int[A][B];
        robots = new Robot[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            String d = st.nextToken();

            int dir = 0;
            if(d.equals("E")) dir = 1;
            else if(d.equals("S")) dir = 2;
            else if(d.equals("W")) dir = 3;

            robots[i] = new Robot(x, y, dir);
            space[x][y] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());

            if (command.equals("F")) {
                while (cnt-- > 0) {
                    if (!moveRobot(n)) return;
                }
            } else if (command.equals("L")) {
                cnt %= 4;
                while(cnt-- > 0){
                    Robot now = robots[n];
                    now.dir = now.dir - 1 == -1 ? 3 : now.dir - 1;
                }
            } else {
                cnt %= 4;
                Robot now = robots[n];
                now.dir = (now.dir + cnt) % 4;
            }
        }

        System.out.println("OK");
    }

    private static boolean moveRobot(int num) {
        Robot now = robots[num];

        int nx = now.x + dx[now.dir];
        int ny = now.y + dy[now.dir];

        if(nx < 0 || ny < 0 || A <= nx || B <= ny){
            System.out.println("Robot " + num + " crashes into the wall");
            return false;
        }

        if(space[nx][ny] != 0){
            System.out.println("Robot " + num + " crashes into robot " + space[nx][ny]);
            return false;
        }

        space[now.x][now.y] = 0;

        now.x = nx;
        now.y = ny;

        space[nx][ny] = num;

        return true;
    }
}
