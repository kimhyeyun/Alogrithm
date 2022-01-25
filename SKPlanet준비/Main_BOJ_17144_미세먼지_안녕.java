import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지_안녕 {
    static int R, C, T;
    static int airCleaner = -1;
    static int[][] amountoFFineDust;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Queue<findDust> dusts;

    static class findDust{
        int x;
        int y;
        int amount;

        public findDust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        amountoFFineDust = new int[R][C];
        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                amountoFFineDust[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(amountoFFineDust[i][j] == -1 && airCleaner == -1) airCleaner = i;
            }
        }

        while (T-- > 0) {
            checkDust();
            spreadDust();
            operateAirCleaner();
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += amountoFFineDust[i][j];
            }
        }
        System.out.println(ans);

    }

    private static void operateAirCleaner() {
        int top = airCleaner;
        int bottom = airCleaner + 1;

        for (int i = top - 1; i > 0; i--) {
            amountoFFineDust[i][0] = amountoFFineDust[i - 1][0];
        }
        for (int i = 0; i < R-1; i++) {
            amountoFFineDust[0][i] = amountoFFineDust[0][i + 1];
        }
        for (int i = 0; i < top - 1; i++) {
            amountoFFineDust[i][C - 1] = amountoFFineDust[i + 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            amountoFFineDust[top][i] = amountoFFineDust[top][i - 1];
        }
        amountoFFineDust[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) {
            amountoFFineDust[i][0] = amountoFFineDust[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            amountoFFineDust[R - 1][i] = amountoFFineDust[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottom ; i--) {
            amountoFFineDust[i][C - 1] = amountoFFineDust[i - 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            amountoFFineDust[bottom][i] = amountoFFineDust[bottom][i - 1];
        }
        amountoFFineDust[bottom][1] = 0;
    }

    private static void spreadDust() {
        while (!dusts.isEmpty()) {
            findDust now = dusts.poll();

            if(now.amount < 5) continue;

            int amountOfSpread = now.amount / 5;
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(amountoFFineDust[nx][ny] == -1)  continue;

                amountoFFineDust[nx][ny] += amountOfSpread;
                cnt++;
            }

            amountoFFineDust[now.x][now.y] -= (amountOfSpread * cnt);
        }
    }

    private static void checkDust() {
        dusts = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (amountoFFineDust[i][j] == -1 || amountoFFineDust[i][j] == 0) continue;

                dusts.add(new findDust(i, j, amountoFFineDust[i][j]));
            }
        }
    }
}
