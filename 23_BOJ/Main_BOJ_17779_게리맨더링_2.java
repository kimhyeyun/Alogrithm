import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17779_게리맨더링_2 {
    static int N, totalPopulation, answer;
    static int[][] populations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N][N];

        totalPopulation = 0;
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                populations[i][j] = Integer.parseInt(st.nextToken());
                totalPopulation += populations[i][j];
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) continue;
                        if(y - d1 < 0 || N <= y + d2) continue;
                        makeBorder(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void makeBorder(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        int[] sumOfPopulations = new int[5];

        // 1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if(border[i][j]) break;
                sumOfPopulations[0] += populations[i][j];
            }
        }

        //2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if(border[i][j]) break;
                sumOfPopulations[1] += populations[i][j];
            }
        }

        //3번 선거구
        for (int i = N - 1; i >= x + d1; i--) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if(border[i][j]) break;
                sumOfPopulations[2] += populations[i][j];
            }
        }

        //4번 선거구
        for (int i = N - 1; i > x + d2; i--) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if(border[i][j]) break;
                sumOfPopulations[3] += populations[i][j];
            }
        }

        int t = sumOfPopulations[0];
        for (int i = 1; i < 4; i++) {
            t += sumOfPopulations[i];
        }

        sumOfPopulations[4] = totalPopulation - t;
        Arrays.sort(sumOfPopulations);

        answer = Math.min(answer, sumOfPopulations[4] - sumOfPopulations[0]);
    }
}
