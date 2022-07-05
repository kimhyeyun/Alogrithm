import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지_안녕 {
    static int R, C, T;
    static int airCleaner = -1;
    static int[][] amountOfFineDust;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        amountOfFineDust = new int[R][C];
        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                amountOfFineDust[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(amountOfFineDust[i][j] == -1 && airCleaner == -1) airCleaner = i;
            }
        }

        while (T-- > 0) {
            extendFineDust();
            workAirCleaner();
        }

        System.out.println(amountOfTotal());

    }

    private static int amountOfTotal() {
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(amountOfFineDust[i][j] > 0) ans += amountOfFineDust[i][j];
            }
        }
        return ans;

    }

    private static void workAirCleaner() {
        for (int i = airCleaner - 1; i > 0; i--) {
            amountOfFineDust[i][0] = amountOfFineDust[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            amountOfFineDust[0][i] = amountOfFineDust[0][i + 1];
        }
        for (int i = 0; i < airCleaner ; i++) {
            amountOfFineDust[i][C - 1] = amountOfFineDust[i + 1][C - 1];
        }
        for (int i = C - 1; i >= 1; i--) {
            amountOfFineDust[airCleaner][i] = amountOfFineDust[airCleaner][i - 1];
        }
        amountOfFineDust[airCleaner][1] = 0;

        int down = airCleaner + 1;
        for (int i = down + 1; i < R - 1; i++) {
            amountOfFineDust[i][0] = amountOfFineDust[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            amountOfFineDust[R - 1][i] = amountOfFineDust[R - 1][i + 1];
        }
        for (int i = R - 1; i > down - 1; i--) {
            amountOfFineDust[i][C - 1] = amountOfFineDust[i - 1][C - 1];
        }
        for (int i = C - 1; i >= 1; i--) {
            amountOfFineDust[down][i] = amountOfFineDust[down][i - 1];
        }
        amountOfFineDust[down][1] = 0;
    }

    private static void extendFineDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp[i][j] += amountOfFineDust[i][j];

                if(amountOfFineDust[i][j] < 5) continue;

                int fineDust = amountOfFineDust[i][j] / 5;

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny || amountOfFineDust[nx][ny] == -1) continue;

                    tmp[nx][ny] += fineDust;
                    cnt += 1;
                }

                tmp[i][j] -= fineDust * cnt;
            }
        }

        for (int i = 0; i < R; i++) {
            System.arraycopy(tmp[i], 0, amountOfFineDust[i], 0, C);
        }
    }


}
