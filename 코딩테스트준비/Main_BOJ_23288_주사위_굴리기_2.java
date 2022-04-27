import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_23288_주사위_굴리기_2 {
    static int N, M, K, x = 0, y = 0, d = 0, ans = 0;
    static int[][] map;
    static int[] garo, sero;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        garo = new int[]{4, 1, 3, 6};
        sero = new int[]{2, 1, 5, 6};

        while (K-- > 0) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) d = changeDir();

            x += dx[d];
            y += dy[d];

            if(d == 0) rollTheDiceToRight(garo, sero);
            else if(d == 1) rollTheDiceToRight(sero, garo);
            else if(d == 2) rollTheDiceToLeft(garo, sero);
            else rollTheDiceToLeft(sero, garo);

            ans += getScore();
            d = nextDir();
        }

        System.out.println(ans);

    }

    private static int nextDir() {
        int mapPoint = map[x][y];
        int dicePoint = garo[3];

        if (mapPoint < dicePoint) {
            if(d + 1 == 4) return 0;
            return d + 1;
        } else if (mapPoint > dicePoint) {
            if (d - 1 == -1) return 3;
            return d - 1;
        }
        return d;
    }

    private static int getScore() {
        int point = map[x][y];
        int cnt = 1;
        boolean[][] isVisited = new boolean[N][M];

        Queue<int[]> q = new LinkedList<>();
        isVisited[x][y] = true;
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny] || map[nx][ny] != point) continue;

                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
                cnt += 1;
            }
        }
        return cnt * point;
    }

    private static void rollTheDiceToLeft(int[] dice, int[] dice_2) {
        int tmp = dice[0];
        for (int i = 0; i < 3; i++) {
            dice[i] = dice[i + 1];
        }
        dice[3] = tmp;
        dice_2[1] = dice[1];
        dice_2[3] = dice[3];
    }

    private static void rollTheDiceToRight(int[] dice, int[] dice_2) {
        int tmp = dice[3];
        for (int i = 3; i > 0; i--) {
            dice[i] = dice[i - 1];
        }
        dice[0] = tmp;
        dice_2[1] = dice[1];
        dice_2[3] = dice[3];
    }


    private static int changeDir() {
        if(d == 0) return 2;
        else if(d == 1) return 3;
        else if(d == 2) return 0;
        return 1;
    }
}
