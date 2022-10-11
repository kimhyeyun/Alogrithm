import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_20055_컨베이어_벨트_위의_로봇 {
    static int N, K;
    static int[] belt;
    static boolean[] isRobot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N + 1];
        isRobot = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 2 * N + 1; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        while (true) {
            answer += 1;
            moveBelt();
            moveRobot();

            if(check()) break;
        }

        System.out.println(answer);
    }

    private static boolean check() {
        int cnt = 0;
        for (int i = 1; i < 2 * N + 1; i++) {
            if(belt[i] == 0) cnt += 1;
        }

        if(cnt >= K) return true;
        return false;
    }

    private static void moveRobot() {
        if(isRobot[N]) isRobot[N] = false;
        for (int i = N - 1; i >= 1; i--) {
            if (isRobot[i] && !isRobot[i + 1]) {
                if (belt[i + 1] > 0) {
                    isRobot[i + 1] = isRobot[i];
                    isRobot[i] = false;
                    belt[i + 1] -= 1;
                }
            }
        }

        if (belt[1] > 0) {
            isRobot[1] = true;
            belt[1] -= 1;
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
