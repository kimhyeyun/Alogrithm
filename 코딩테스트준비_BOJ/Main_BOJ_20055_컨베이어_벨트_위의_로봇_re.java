import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어_벨트_위의_로봇_re {
    static int N, K;
    static int[] belt;
    static boolean[] isRobot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        belt = new int[2 * N + 1];
        isRobot = new boolean[N + 1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i < 2 * N + 1; i++) {
            belt[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int ans = 0;
        while (true) {
            ans += 1;

            moveBelt();
            moveRobot();

            if (isEnd()) break;
        }
        System.out.println(ans);
    }

    private static boolean isEnd() {
        int s = 0;
        for (int i = 1; i < 2 * N + 1; i++) {
            if(belt[i] == 0) s += 1;
        }

        if(s >= K) return true;
        return false;
    }

    private static void moveRobot() {
        isRobot[N] = false;
        for (int i = N; i > 1; i--) {
            if (!isRobot[i] && isRobot[i - 1] && belt[i] > 0) {
                isRobot[i] = true;
                isRobot[i - 1] = false;
                belt[i] -= 1;
            }
        }

        if (belt[1] > 0) {
            belt[1] -= 1;
            isRobot[1] = true;
        }
    }

    private static void moveBelt() {
        int tmp = belt[2 * N];
        for (int i = 2 * N; i > 1; i--) {
            belt[i] = belt[i - 1];
        }
        belt[1] = tmp;

        for (int i = N; i > 1; i--) {
            isRobot[i] = isRobot[i - 1];
        }
        isRobot[1] = false;
    }
}
