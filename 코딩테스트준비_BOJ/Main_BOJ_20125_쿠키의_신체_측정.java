import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20125_쿠키의_신체_측정 {
    static int N;
    static int[] head, heart, endOfWaist;
    static char[][] cookie;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cookie = new char[N][N];
        head = null;

        for (int i = 0; i < N; i++) {
            cookie[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (head == null && cookie[i][j] == '*') {
                    head = new int[]{i, j};
                }
            }
        }

        heart = new int[]{head[0] + 1, head[1]};

        int leftArm = findLeftArmLen();
        int rightArm = findRightArmLen();
        int waist = findWaistLen();
        int leftLeg = findLeftLegLen();
        int rightLeg = findRightLegLen();

        heart[0] += 1;
        heart[1] += 1;

        System.out.println(heart[0] + " " + heart[1]);
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);

    }

    private static int findRightLegLen() {
        int cnt = 0;
        int y = endOfWaist[1] + 1;
        for (int x = endOfWaist[0] + 1; x < N; x++) {
            if(cookie[x][y] == '*') cnt += 1;
            else break;
        }
        return cnt;
    }

    private static int findLeftLegLen() {
        int cnt = 0;
        int y = endOfWaist[1] - 1;
        for (int x = endOfWaist[0] + 1; x < N; x++) {
            if(cookie[x][y] == '*') cnt += 1;
            else break;
        }
        return cnt;
    }

    private static int findWaistLen() {
        int cnt = 0;
        for (int x = heart[0] + 1; x < N; x++) {
            if (cookie[x][heart[1]] == '*') {
                cnt += 1;
            } else {
                endOfWaist = new int[]{x - 1, heart[1]};
                break;
            }
        }
        return cnt;
    }

    private static int findRightArmLen() {
        int cnt = 0;
        for (int y = heart[1] + 1; y < N; y++) {
            if(cookie[heart[0]][y] == '*') cnt += 1;
            else break;
        }
        return cnt;
    }

    private static int findLeftArmLen() {
        int cnt = 0;
        for (int y = heart[1] - 1; y >= 0; y--) {
            if(cookie[heart[0]][y] == '*') cnt += 1;
            else break;
        }
        return cnt;
    }

}
