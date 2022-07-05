import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14891_톱니바퀴_re {
    static int[][] gears;
    static int[] dirs;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        gears = new int[4][8];
        for (int i = 0; i < 4; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < c.length; j++) {
                gears[i][j] = c[j] == '1' ? 1 : 0;
            }
        }

        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int dir = Integer.parseInt(stringTokenizer.nextToken());

            dirs = new int[4];
            dirs[g] = dir;

            for (int i = g; i < 3; i++) {
                // 오른쪽
                if(gears[i][2] != gears[i+1][6]) dirs[i + 1] = dirs[i] * -1;
            }
            for (int i = g; i > 0; i--) {
                //왼쪽
                if (gears[i][6] != gears[i - 1][2]) dirs[i - 1] = dirs[i] * -1;
            }

            for (int i = 0; i < 4; i++) {
                if(dirs[i] == 0) continue;
                rotateGear(i, dirs[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                ans += Math.pow(2, i);
            }
        }
        System.out.println(ans);
    }

    private static void rotateGear(int g, int dir) {
        if (dir == 1) {
            // 시계방향
            int tmp = gears[g][7];
            for (int i = 7; i > 0; i--) {
                gears[g][i] = gears[g][i - 1];
            }
            gears[g][0] = tmp;
        } else {
            // 반시계방향
            int tmp = gears[g][0];
            for (int i = 0; i < 7; i++) {
                gears[g][i] = gears[g][i + 1];
            }
            gears[g][7] = tmp;
        }
    }
}
