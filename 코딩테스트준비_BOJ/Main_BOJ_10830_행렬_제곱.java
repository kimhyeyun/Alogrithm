import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10830_행렬_제곱 {
    final static int MOD = 1000;
    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        B = Long.parseLong(stringTokenizer.nextToken());

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(stringTokenizer.nextToken()) % MOD;
            }
        }

        int[][] result = pow(matrix, B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] pow(int[][] m, long exp) {
        if(exp == 1L) return m;

        int[][] tmp = pow(m, exp / 2);

        tmp = multiply(tmp, tmp);

        if(exp % 2 == 1L) tmp = multiply(tmp, m);

        return tmp;
    }

    private static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    tmp[i][j] += m1[i][k] * m2[k][j];
                    tmp[i][j] %= MOD;
                }
            }
        }
        return tmp;
    }
}
