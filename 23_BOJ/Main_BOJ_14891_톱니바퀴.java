import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14891_톱니바퀴 {
    static int K;
    static int[][] gears;
    static int[] dir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                gears[i][j] = str.charAt(j) == '1' ? 1 : 0;
            }
        }

        K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            dir = new int[4];
            dir[num] = d;
            for (int j = num - 1; j >= 0; j--) {
                if (gears[j + 1][6] != gears[j][2]) {
                    dir[j] = dir[j + 1] * -1;
                }else break;
            }

            for (int j = num + 1; j < 4; j++) {
                if (gears[j - 1][2] != gears[j][6]) {
                    dir[j] = dir[j - 1] * -1;
                }else break;
            }

            for (int i = 0; i < 4; i++) {
                rotateGear(i);
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                answer += Math.pow(2, i);
            }
        }
        System.out.println(answer);
    }

    private static void rotateGear(int num) {
        if (dir[num] == 1) {
            // 시계방향
            int tmp = gears[num][7];
            for (int i = 7; i > 0; i--) {
                gears[num][i] = gears[num][i - 1];
            }
            gears[num][0] = tmp;

        }else if(dir[num] == -1){
            // 반시계 방향
            int tmp = gears[num][0];
            for (int i = 0; i < 7; i++) {
                gears[num][i] = gears[num][i + 1];
            }
            gears[num][7] = tmp;
        }
    }
}
