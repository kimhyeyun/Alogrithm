import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17778_게리맨더링_2 {

    static int N;
    static int[][] A;
    static int totalOfPeople = 0;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                totalOfPeople += A[i][j];
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if(x + d1 + d2 >= N) continue;
                        if(y - d1 < 0 || y + d2 >= N) continue;

                        solution(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void solution(int x, int y, int d1, int d2) {
        boolean[][] borders = new boolean[N][N];

        //경계선
        for (int i = 0; i <= d1; i++) {
            borders[x + i][y - i] = true;
            borders[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            borders[x + i][y + i] = true;
            borders[x + d1 + i][y - d1 + i] = true;
        }

        int[] sumOfPeople = new int[6];

        // 1구역
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if(borders[i][j]) break;
                sumOfPeople[1] += A[i][j];
            }
        }

        // 2구역
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if(borders[i][j]) break;
                sumOfPeople[2] += A[i][j];
            }
        }

        // 3구역
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if(borders[i][j]) break;
                sumOfPeople[3] += A[i][j];
            }
        }

        // 4구역
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (borders[i][j]) break;
                sumOfPeople[4] += A[i][j];
            }
        }

        // 5구역
        sumOfPeople[5] = totalOfPeople;
        for (int i = 1; i < 5; i++) {
            sumOfPeople[5] -= sumOfPeople[i];
        }

        Arrays.sort(sumOfPeople);

        ans = sumOfPeople[5] - sumOfPeople[1] < ans ? sumOfPeople[5] - sumOfPeople[1] : ans;
    }

}
