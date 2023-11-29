import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어_벨트_위의_로봇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2 * N];
        boolean[] isRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        while (true) {
            answer += 1;

            rotateBelt(N, belt, isRobot);
            moveRobot(N, isRobot, belt);

            if(isEnd(belt, N, K)) break;
        }

        System.out.println(answer);
    }

    private static boolean isEnd(int[] belt, int N, int K) {
        int count = 0;

        for (int i = 0; i < 2 * N; i++) {
            if(belt[i] == 0) count += 1;
        }

        return count >= K;
    }

    private static void moveRobot(int N, boolean[] isRobot, int[] belt) {
        if(isRobot[N - 1]) isRobot[N - 1] = false;

        for (int i = N - 1; i > 0; i--) {
            if (!isRobot[i] && isRobot[i - 1]) {
                if (belt[i] > 0) {
                    isRobot[i] = true;
                    isRobot[i - 1] = false;
                    belt[i] -= 1;
                }
            }
        }

        if (belt[0] > 0) {
            isRobot[0] = true;
            belt[0] -= 1;
        }
    }

    private static void rotateBelt(int N, int[] belt, boolean[] isRobot) {
        int tmp = belt[2 * N - 1];

        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = tmp;

        for (int i = N - 1; i > 0; i--) {
            isRobot[i] = isRobot[i - 1];
        }

        isRobot[0] = false;
    }
}
