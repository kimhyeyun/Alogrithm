import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17780_새로운_게임_re {

    static class horse {
        int x, y, dir;

        public horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static final int WHITE = 0, RED = 1, BLUE = 2;
    static int N, K;
    static horse[] horses;
    static List<Integer>[][] horseList;
    static int[][] mapColor;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        horses = new horse[K + 1];
        horseList = new List[N][N];
        mapColor = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mapColor[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                horseList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i < K + 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            horses[i] = new horse(x, y, d);
            horseList[x][y].add(i);
        }

        for (int time = 1; time <= 1000; time++) {
            for (int i = 1; i < K + 1; i++) {
                if (move(i)) {
                    System.out.println(time);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean move(int num) {
        horse now = horses[num];

        int heightOfNow = horseList[now.x][now.y].indexOf(num);
        if(heightOfNow != 0) return false;

        int nx = now.x + dx[now.dir];
        int ny = now.y + dy[now.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || mapColor[nx][ny] == BLUE) {
            changeDir(now);
            nx = now.x + dx[now.dir];
            ny = now.y + dy[now.dir];
        }

        if(nx < 0 || ny < 0 || N <= nx || N <= ny || mapColor[nx][ny] == BLUE) return false;

        moveHorse(num, now, nx, ny);

        if(horseList[nx][ny].size() > 3) return true;

        return false;
    }

    private static void moveHorse(int num, horse now, int nx, int ny) {
        List<Integer> from = horseList[now.x][now.y];
        List<Integer> to = horseList[nx][ny];

        int heightOfNow = from.indexOf(num);

        switch (mapColor[nx][ny]) {
            case WHITE:
                for (int i = heightOfNow; i < from.size(); i++) {
                    int h = from.get(i);
                    horses[h].x = nx;
                    horses[h].y = ny;

                    to.add(h);
                }
                for (int i = from.size() - 1; i >= heightOfNow; i--) {
                    from.remove(i);
                }
                break;
            case RED:
                for (int i = from.size() - 1; i >= heightOfNow; i--) {
                    int h = from.get(i);
                    horses[h].x = nx;
                    horses[h].y = ny;

                    to.add(h);
                }

                for (int i = from.size() - 1; i >= heightOfNow; i--) {
                    from.remove(i);
                }
                break;
        }
    }

    private static void changeDir(horse now) {
        if(now.dir == 0) now.dir = 1;
        else if(now.dir == 1) now.dir = 0;
        else if(now.dir == 2) now.dir = 3;
        else now.dir = 2;
    }
}
