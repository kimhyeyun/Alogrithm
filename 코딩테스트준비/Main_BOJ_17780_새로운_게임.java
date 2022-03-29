import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17780_새로운_게임 {
    static class horse {
        int x,y, dir;

        public horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, K;
    static int[][] colors;
    static List<Integer>[][] horsesOfBoard;
    static horse[] horses;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0,};
    static final int WHITE = 0, RED = 1, BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        horsesOfBoard = new List[N][N];
        colors = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colors[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                horsesOfBoard[i][j] = new ArrayList<>();
            }
        }

        horses = new horse[K];
        for (int k = 0; k < K; k++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken());

            horsesOfBoard[x][y].add(k);
            horses[k] = new horse(x, y, d);
        }

        gameStart();
    }

    private static void gameStart() {
        for (int time = 1; time <= 1000; time++) {
            for (int i = 0; i < K; i++) {
                if (move(i)) {
                    System.out.println(time);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean move(int num) {
        horse cur = horses[num];

        int heightOfNow = horsesOfBoard[cur.x][cur.y].indexOf(num);
        if(heightOfNow != 0) return false;

        int nx = cur.x + dx[cur.dir];
        int ny = cur.y + dy[cur.dir];

        if (nx < 0 || ny < 0 || N <= nx || N <= ny || colors[nx][ny] == BLUE) {
            changeDir(cur);
            nx = cur.x + dx[cur.dir];
            ny = cur.y + dy[cur.dir];
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && colors[nx][ny] != BLUE) {
            moveHorse(num, cur, nx, ny);

            if(horsesOfBoard[nx][ny].size() > 3) return true;
        }

        return false;
    }

    private static void moveHorse(int num, horse cur, int x, int y) {
        List<Integer> from = horsesOfBoard[cur.x][cur.y];
        List<Integer> to = horsesOfBoard[x][y];

        int heightOfNow = from.indexOf(num);

        switch (colors[x][y]) {
            case WHITE:
                for (int i = heightOfNow; i < from.size(); i++) {
                    int horse = from.get(i);
                    updateHorse(horse, x, y);
                    to.add(horse);
                }
                removeHorses(heightOfNow, from.size() - 1, from);
                break;

            case RED:
                for (int i = from.size() - 1; i >= heightOfNow; i--) {
                    int horse = from.get(i);
                    updateHorse(horse, x, y);
                    to.add(horse);
                }
                removeHorses(heightOfNow, from.size() - 1, from);
                break;
        }

    }

    private static void removeHorses(int heightOfNow, int last, List<Integer> from) {
        for (int i = last; i >= heightOfNow; i--) {
            from.remove(i);
        }
    }

    private static void updateHorse(int horse, int nx, int ny) {
        horses[horse].x = nx;
        horses[horse].y = ny;
    }

    private static void changeDir(horse cur) {
        if(cur.dir == 1) cur.dir = 2;
        else if(cur.dir == 2) cur.dir = 1;
        else if(cur.dir == 3) cur.dir = 4;
        else cur.dir = 3;
    }
}
