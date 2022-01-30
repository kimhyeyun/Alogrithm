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
    static Queue<fineDust> dusts;

    static class fineDust {
        int x;
        int y;
        int amount;

        public fineDust(int x, int y, int amount) {
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

        amountOfFineDust = new int[R][C];
        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                amountOfFineDust[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(amountOfFineDust[i][j] == -1 && airCleaner == -1) airCleaner = i;
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
                ans += amountOfFineDust[i][j];
            }
        }
        System.out.println(ans);

    }

    private static void operateAirCleaner() {
        int top = airCleaner;
        int bottom = airCleaner + 1;

        for (int i = top - 1; i > 0; i--) {
            amountOfFineDust[i][0] = amountOfFineDust[i - 1][0];
        }
        for (int i = 0; i < R-1; i++) {
            amountOfFineDust[0][i] = amountOfFineDust[0][i + 1];
        }
        for (int i = 0; i < top - 1; i++) {
            amountOfFineDust[i][C - 1] = amountOfFineDust[i + 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            amountOfFineDust[top][i] = amountOfFineDust[top][i - 1];
        }
        amountOfFineDust[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) {
            amountOfFineDust[i][0] = amountOfFineDust[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            amountOfFineDust[R - 1][i] = amountOfFineDust[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottom ; i--) {
            amountOfFineDust[i][C - 1] = amountOfFineDust[i - 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            amountOfFineDust[bottom][i] = amountOfFineDust[bottom][i - 1];
        }
        amountOfFineDust[bottom][1] = 0;
    }

    private static void spreadDust() {
        while (!dusts.isEmpty()) {
            fineDust now = dusts.poll();

            if(now.amount < 5) continue;

            int amountOfSpread = now.amount / 5;
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(amountOfFineDust[nx][ny] == -1)  continue;

                amountOfFineDust[nx][ny] += amountOfSpread;
                cnt++;
            }

            amountOfFineDust[now.x][now.y] -= (amountOfSpread * cnt);
        }
    }

    private static void checkDust() {
        dusts = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (amountOfFineDust[i][j] == -1 || amountOfFineDust[i][j] == 0) continue;

                dusts.add(new fineDust(i, j, amountOfFineDust[i][j]));
            }
        }
    }
}
