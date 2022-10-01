import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14891_톱니바퀴 {
    static int K, answer;
    static int[][] gears;
    static int[] dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;
        gears = new int[4][8];

        for (int i = 0; i < 4; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = c[j] == '1' ? 1 : 0;
            }
        }

        K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            dir = new int[4];

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            dir[num] = d;

            checkDir(num);
            rotateGear();

        }
        for (int i = 0; i < 4; i++) {
            if(gears[i][0] == 1) answer += Math.pow(2, i);
        }
        System.out.println(answer);
    }

    private static void rotateGear() {
        for (int i = 0; i < 4; i++) {
            if(dir[i] == 0) continue;
            if (dir[i] == 1) {
                int tmp = gears[i][7];
                for (int j = 7; j > 0; j--) {
                    gears[i][j] = gears[i][j - 1];
                }
                gears[i][0] = tmp;
            } else {
                int tmp = gears[i][0];
                for (int j = 0; j < 7; j++) {
                    gears[i][j] = gears[i][j + 1];
                }
                gears[i][7] = tmp;
            }
        }
    }

    private static void checkDir(int num) {
        for (int i = num; i > 0; i--) {
            if(gears[i][6] != gears[i - 1][2]) dir[i - 1] = dir[i] * -1;
            else break;
        }
        for (int i = num; i < 3; i++) {
            if(gears[i][2] != gears[i + 1][6]) dir[i + 1] = dir[i] * -1;
            else break;
        }
    }
}
