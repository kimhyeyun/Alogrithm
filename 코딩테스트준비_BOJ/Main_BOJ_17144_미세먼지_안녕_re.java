import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지_안녕_re {
    static int R, C, T, airConditioner;
    static int[][] A;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        A = new int[R][C];
        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(A[i][j] == -1) airConditioner = i;
            }
        }

        while (T-- > 0) {
            spreadFineDust();
            workAirConditioner();
        }
        System.out.println(checkRemaining());
    }

    private static void print() {
        for (int a[] : A) {
            for (int i : a) {
                System.out.print(i +" ");
            }
            System.out.println();
        }
    }

    private static int checkRemaining() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(A[i][j] > 0) sum += A[i][j];
            }
        }
        return sum;
    }

    private static void workAirConditioner() {
        int up = airConditioner - 1;
        int down = airConditioner;

        for (int i = up - 1; i > 0; i--) A[i][0] = A[i - 1][0];
        for (int j = 0; j < C - 1; j++) A[0][j] = A[0][j + 1];
        for (int i = 0; i < up; i++) A[i][C - 1] = A[i + 1][C - 1];
        for (int j = C - 1; j > 0; j--) A[up][j] = A[up][j - 1];

        A[up][1] = 0;

        for (int i = down + 1; i < R - 1; i++) A[i][0] = A[i + 1][0];
        for (int j = 0; j < C - 1; j++) A[R - 1][j] = A[R - 1][j + 1];
        for (int i = R - 1; i > down; i--) A[i][C - 1] = A[i - 1][C - 1];
        for (int j = C - 1; j > 0; j--) A[down][j] = A[down][j - 1];

        A[down][1] = 0;
    }

    private static void spreadFineDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp[i][j] += A[i][j];

                if(A[i][j] < 5) continue;

                int fineDust = A[i][j] / 5;

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                    if(A[nx][ny] == -1) continue;

                    tmp[nx][ny] += fineDust;
                    cnt += 1;
                }
                tmp[i][j] -= (fineDust * cnt);
            }
        }

        for (int i = 0; i < R; i++) {
            System.arraycopy(tmp[i], 0, A[i], 0, C);
        }
    }
}
