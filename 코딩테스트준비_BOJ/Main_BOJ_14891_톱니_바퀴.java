import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_14891_톱니_바퀴 {

    static int[][] cogWheel = new int[4][8];
    static int[] dir;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        for (int i = 0; i < 4; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                cogWheel[i][j] = arr[j] == '1' ? 1 : 0;
            }
        }

        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken());

            dir = new int[4];

            dir[num] = d;
            for (int i = num - 1; i >= 0; i--) {
                if (cogWheel[i + 1][6] != cogWheel[i][2]) {
                    dir[i] = dir[i + 1] * -1;
                }
            }

            for (int i = num + 1; i < 4; i++) {
                if (cogWheel[i - 1][2] != cogWheel[i][6]) {
                    dir[i] = dir[i - 1] * -1;
                }
            }

            for (int i = 0; i < 4; i++) {
                if(dir[i] == 0) continue;
                if(dir[i] == 1) rotateRight(i);
                else rotateLeft(i);
            }
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if(cogWheel[i][0] == 1)
                ans += (int) Math.pow(2, i);
        }
        System.out.println(ans);
    }

    private static void rotateLeft(int num) {
        int tmp = cogWheel[num][0];
        for (int i = 0; i < 7; i++) {
            cogWheel[num][i] = cogWheel[num][i + 1];
        }
        cogWheel[num][7] = tmp;
    }

    private static void rotateRight(int num) {
        int tmp = cogWheel[num][7];
        for (int i = 7; i > 0; i--) {
            cogWheel[num][i] = cogWheel[num][i - 1];
        }
        cogWheel[num][0] = tmp;
    }
}
