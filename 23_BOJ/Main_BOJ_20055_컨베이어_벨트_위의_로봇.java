import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20055_컨베이어_벨트_위의_로봇 {
    static int N, K;
    static int[] belt;
    static boolean[] isExistedRobots;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N + 1];
        isExistedRobots = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        while (true) {
            answer += 1;
            moveBelt();
            moveRobots();

            if(isEnd()) break;
        }
        System.out.println(answer);
    }

    private static boolean isEnd() {
        int count = 0;

        for (int i = 1; i <= 2 * N; i++) {
            if(belt[i] == 0) count += 1;
        }
        if(count >= K) return true;
        return false;
    }

    private static void moveRobots() {
        if(isExistedRobots[N]) isExistedRobots[N] = false;

        for (int i = N; i > 1; i--) {
            if (isExistedRobots[i - 1] && !isExistedRobots[i]) {
                if (belt[i] > 0) {
                    isExistedRobots[i] = isExistedRobots[i - 1];
                    isExistedRobots[i - 1] = false;
                    belt[i] -= 1;
                }
            }
        }

        if (belt[1] > 0) {
            isExistedRobots[1] = true;
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
            isExistedRobots[i] = isExistedRobots[i - 1];
        }
        isExistedRobots[1] = false;
    }
}
