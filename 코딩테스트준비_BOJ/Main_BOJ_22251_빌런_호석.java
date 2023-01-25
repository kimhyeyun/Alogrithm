import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_22251_빌런_호석 {
    static int N, K, P, X;
    static String realK;
    static int[][] change = new int[10][10];
    static boolean[][] display = new boolean[10][7];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        P = Integer.parseInt(stringTokenizer.nextToken());
        X = Integer.parseInt(stringTokenizer.nextToken());

        initDisplay();

        int tmp = 1000000;
        realK = Integer.toString(tmp + X).substring(7 - K, 7);

        System.out.println(sol());

    }

    private static int sol() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int tmp = 1000000;
            String tmp2 = Integer.toString(tmp + i).substring(7 - K, 7);

            int cha = 0;
            for (int j = 0; j < K; j++) {
                cha += Math.max(
                        change[realK.charAt(j) - '0'][tmp2.charAt(j) - '0'],
                        change[tmp2.charAt(j) - '0'][realK.charAt(j) - '0']
                );
            }
            if (cha >= 1 && cha <= P) {
                answer += 1;
            }
        }
        return answer;
    }

    private static void initDisplay() {
        display[0][0] = true;
        display[0][1] = true;
        display[0][2] = true;
        display[0][4] = true;
        display[0][5] = true;
        display[0][6] = true;

        display[1][2] = true;
        display[1][5] = true;

        display[2][0] = true;
        display[2][2] = true;
        display[2][3] = true;
        display[2][4] = true;
        display[2][6] = true;

        display[3][0] = true;
        display[3][2] = true;
        display[3][3] = true;
        display[3][5] = true;
        display[3][6] = true;

        display[4][1] = true;
        display[4][2] = true;
        display[4][3] = true;
        display[4][5] = true;

        display[5][0] = true;
        display[5][1] = true;
        display[5][3] = true;
        display[5][5] = true;
        display[5][6] = true;

        display[6][0] = true;
        display[6][1] = true;
        display[6][3] = true;
        display[6][4] = true;
        display[6][5] = true;
        display[6][6] = true;

        display[7][0] = true;
        display[7][2] = true;
        display[7][5] = true;

        display[8][0] = true;
        display[8][1] = true;
        display[8][2] = true;
        display[8][3] = true;
        display[8][4] = true;
        display[8][5] = true;
        display[8][6] = true;

        display[9][0] = true;
        display[9][1] = true;
        display[9][2] = true;
        display[9][3] = true;
        display[9][5] = true;
        display[9][6] = true;

        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int t = 0; t < 7; t++) {
                    if (display[i][t] != display[j][t]) {
                        change[i][j] += 1;
                    }
                }
            }
        }
    }
}
