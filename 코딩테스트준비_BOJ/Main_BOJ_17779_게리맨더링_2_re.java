import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17779_게리맨더링_2_re {
    static int N, totalOfPeople, ans;
    static int[][] city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        totalOfPeople = 0;
        ans = Integer.MAX_VALUE;
        city = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                totalOfPeople += city[i][j];
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if(x + d1 + d2 >= N) continue;
                        if(y - d1 < 0 || N <= y +  d2) continue;

                        divideLine(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void divideLine(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int[] sumOfPeople = new int[6];

        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if(border[i][j]) break;
                sumOfPeople[1] += city[i][j];
            }
        }

        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if(border[i][j]) break;
                sumOfPeople[2] += city[i][j];
            }
        }

        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if(border[i][j]) break;
                sumOfPeople[3] += city[i][j];
            }
        }

        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if(border[i][j]) break;
                sumOfPeople[4] += city[i][j];
            }
        }

        sumOfPeople[5] = totalOfPeople;
        for (int i = 1; i < 5; i++) {
            sumOfPeople[5] -= sumOfPeople[i];
        }

        Arrays.sort(sumOfPeople);

        ans = sumOfPeople[5] - sumOfPeople[1] < ans ? sumOfPeople[5] - sumOfPeople[1] : ans;
    }
}
