import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17070_파이프_옮기기_1 {
    static int N;
    static int[][] house;
    static int[][][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        DP = new int[N + 1][N + 1][3];
        house = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                house[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        DP[1][2][0] = 1;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 3; j < N + 1; j++) {
                if (house[i][j] == 0) {
                    DP[i][j][0] = DP[i][j - 1][0] + DP[i][j - 1][2];
                    DP[i][j][1] = DP[i - 1][j][1] + DP[i - 1][j][2];

                    if (house[i - 1][j] == 0 && house[i][j - 1] == 0) {
                        DP[i][j][2] = DP[i - 1][j - 1][0] + DP[i - 1][j - 1][1] + DP[i - 1][j - 1][2];
                    }
                }
            }
        }

        System.out.println(DP[N][N][0] + DP[N][N][1] + DP[N][N][2]);

    }
}
