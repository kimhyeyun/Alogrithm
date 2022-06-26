import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19236_청소년_상어_re {
    static class Fish {
        int num, x, y, dir;
        boolean alive;

        public Fish(int num, int x, int y, int dir, boolean alive) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = alive;
        }
    }

    static final int SHARK = -1, EMPTY = 0;
    static int[][] map;
    static Fish[] fishes;
    static int ans;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        map = new int[4][4];
        fishes = new Fish[17];
        ans = 0;

        for (int i = 0; i < 4; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(stringTokenizer.nextToken());
                int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;

                map[i][j] = a;
                fishes[a] = new Fish(a, i, j, b, true);
            }
        }

        int sharkX = 0, sharkY = 0;
        int sharkDir = fishes[map[0][0]].dir;
        int eat = map[0][0];
        fishes[map[0][0]].alive = false;
        map[0][0] = SHARK;

        DFS(sharkX, sharkY, sharkDir, eat);

        System.out.println(ans);
    }

    private static void DFS(int sharkX, int sharkY, int sharkDir, int eat) {
        ans = Math.max(ans, eat);

        int[][] tmp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, 4);
        }

        Fish[] tmpFishes = new Fish[17];
        for (int i = 1; i < 17; i++) {
            tmpFishes[i] = new Fish(fishes[i].num, fishes[i].x, fishes[i].y, fishes[i].dir, fishes[i].alive);
        }

        moveFishes();
        moveShark(sharkX, sharkY, sharkDir, eat);

        for (int i = 0; i < 4; i++) {
            System.arraycopy(tmp[i], 0, map[i], 0, 4);
        }
        for (int i = 1; i < 17; i++) {
            fishes[i] = new Fish(tmpFishes[i].num, tmpFishes[i].x, tmpFishes[i].y, tmpFishes[i].dir, tmpFishes[i].alive);
        }
    }

    private static void moveShark(int sharkX, int sharkY, int sharkDir, int eat) {
        for (int i = 1; i < 4; i++) {
            int nx = sharkX + dx[sharkDir] * i;
            int ny = sharkY + dy[sharkDir] * i;

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == EMPTY) continue;

            int eatFish = map[nx][ny];
            int dir = fishes[map[nx][ny]].dir;

            map[sharkX][sharkY] = EMPTY;
            map[nx][ny] = SHARK;
            fishes[eatFish].alive = false;

            DFS(nx, ny, dir, eat + eatFish);

            fishes[eatFish].alive = true;
            map[sharkX][sharkY] = SHARK;
            map[nx][ny] = eatFish;
        }
    }

    private static void moveFishes() {
        for (int i = 1; i < 17; i++) {
            if(!fishes[i].alive) continue;

            int cnt = 0;
            int dir = fishes[i].dir;
            int nx = 0, ny = 0;

            while (cnt < 8) {
                nx = fishes[i].x + dx[dir];
                ny = fishes[i].y + dy[dir];

                if (nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == SHARK) {
                    dir = (dir + 1) % 8;
                    fishes[i].dir = dir;
                    cnt += 1;
                    continue;
                }

                else if (map[nx][ny] == EMPTY) {
                    map[fishes[i].x][fishes[i].y] = EMPTY;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    map[nx][ny] = i;
                } else {
                    int changeFish = map[nx][ny];

                    fishes[changeFish].x = fishes[i].x;
                    fishes[changeFish].y = fishes[i].y;
                    map[fishes[i].x][fishes[i].y] = changeFish;

                    map[nx][ny] = i;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                }
                break;
            }
        }
    }
}
