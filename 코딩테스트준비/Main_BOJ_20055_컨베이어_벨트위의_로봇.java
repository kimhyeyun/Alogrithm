import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어_벨트위의_로봇 {

    static int N, K, turn = 0;
    static int[] durabilityOfBelt;
    static boolean[] isRobot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        durabilityOfBelt = new int[2 * N];
        isRobot = new boolean[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            durabilityOfBelt[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        while (true) {
            turn += 1;

            moveBelt();
            moveRobot();

            if(isExit()) break;
        }
        System.out.println(turn);
    }

    private static boolean isExit() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if(durabilityOfBelt[i] == 0) cnt += 1;
        }

        return cnt >= K;
    }

    private static void moveRobot() {
        isRobot[N - 1] = false;

        for (int i = N - 2; i >= 0; i--) {
            if (!isRobot[i + 1] && isRobot[i] && durabilityOfBelt[i + 1] > 0) {
                isRobot[i + 1] = true;
                isRobot[i] = false;
                durabilityOfBelt[i + 1] -= 1;
            }
        }

        if(durabilityOfBelt[0] > 0){
            isRobot[0] = true;
            durabilityOfBelt[0] -= 1;
        }
    }

    private static void moveBelt() {
        int tmp = durabilityOfBelt[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            durabilityOfBelt[i] = durabilityOfBelt[i - 1];
        }
        durabilityOfBelt[0] = tmp;

        for (int i = N - 2; i >= 0; i--) {
            isRobot[i + 1] = isRobot[i];
        }
        isRobot[0] = false;
    }
}
