import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_19236_청소년_상어 {

    static class Fish{
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

    static int[][] map;
    static Fish[] fishes;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int amountOfEating = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        map = new int[4][4];
        fishes = new Fish[17];
        for (int i = 0; i < 4; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(stringTokenizer.nextToken());
                int dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;

                map[i][j] = num;
                fishes[num] = new Fish(num, i, j, dir, true);
            }
        }

        int sx = 0, sy = 0;
        int sd = fishes[map[0][0]].dir;
        int eat = map[0][0];
        fishes[map[0][0]].alive = false;
        map[0][0] = -1;

        DFS(sx, sy, sd, eat);

        System.out.println(amountOfEating);
    }

    private static void DFS(int sx, int sy, int sd, int eat) {
        amountOfEating = Math.max(amountOfEating, eat);

        int[][] tmpMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
//            System.arraycopy(map[i], 0, tmpMap[i], 0, map.length);
            tmpMap[i] = Arrays.copyOf(map[i], 4);
        }

        Fish[] tmpFishes = new Fish[17];
        for (int i = 1; i < 17; i++) {
            tmpFishes[i] = new Fish(fishes[i].num, fishes[i].x, fishes[i].y, fishes[i].dir, fishes[i].alive);
        }

        moveFishes();
        moveShark(sx, sy, sd, eat);

        for (int i = 0; i < 4; i++) {
//            System.arraycopy(tmpMap[i], 0, map[i], 0, 4);
            map[i] = Arrays.copyOf(tmpMap[i], 4);
        }

        for (int i = 1; i < 17; i++) {
            fishes[i] = new Fish(tmpFishes[i].num, tmpFishes[i].x, tmpFishes[i].y, tmpFishes[i].dir, tmpFishes[i].alive);
        }
    }

    private static void moveShark(int sx, int sy, int sd, int eat) {
        for (int i = 1; i < 4; i++) {
            int nx = sx + dx[sd] * i;
            int ny = sy + dy[sd] * i;

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == 0) continue;

            int eatFish = map[nx][ny];
            int nd = fishes[eatFish].dir;
            map[sx][sy] = 0;
            map[nx][ny] = -1;
            fishes[eatFish].alive = false;

            DFS(nx, ny, nd, eat + eatFish);

            fishes[eatFish].alive = true;
            map[sx][sy] = -1;
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

                if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == -1){
                    dir = (dir + 1) % 8;
                    fishes[i].dir = dir;
                    cnt += 1;
                    continue;
                }
                else if (map[nx][ny] == 0) {
                    map[fishes[i].x][fishes[i].y] = 0;
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    map[nx][ny] = i;
                } else {
                    int changeFish = fishes[map[nx][ny]].num;
                    fishes[changeFish].x = fishes[i].x;
                    fishes[changeFish].y = fishes[i].y;
                    map[fishes[changeFish].x][fishes[changeFish].y] = changeFish;

                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    map[nx][ny] = i;
                }
                break;
            }
        }
    }
}
