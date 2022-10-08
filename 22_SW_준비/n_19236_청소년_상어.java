import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_19236_청소년_상어 {
    static class fish {
        int num, x, y, dir;
        boolean isAlive;

        public fish(int num, int x, int y, int dir, boolean isAlive) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static final int SHARK = -1, EMPTY = 0;
    static int answer;
    static int[][] map;
    static fish[] fishes;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[4][4];
        fishes = new fish[17];
        answer = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                fishes[num] = new fish(num, i, j, dir, true);
                map[i][j] = num;
            }
        }

        int eatFish = map[0][0];
        fishes[map[0][0]].isAlive = false;
        map[0][0] = SHARK;

        DFS(new int[]{0, 0, fishes[eatFish].dir, eatFish});

        System.out.println(answer);
    }

    private static void DFS(int[] shark) {
        answer = Math.max(answer, shark[3]);

        int[][] tmpMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, tmpMap[i], 0, 4);
        }

        fish[] tmpFishes = new fish[17];
        for (int i = 1; i < 17; i++) {
            tmpFishes[i] = new fish(fishes[i].num, fishes[i].x, fishes[i].y, fishes[i].dir, fishes[i].isAlive);
        }

        moveFishes();
        moveShark(shark);

        for (int i = 0; i < 4; i++) {
            System.arraycopy(tmpMap[i], 0, map[i], 0, 4);
        }

        for (int i = 1; i < 17; i++) {
            fishes[i] = new fish(tmpFishes[i].num, tmpFishes[i].x, tmpFishes[i].y, tmpFishes[i].dir, tmpFishes[i].isAlive);
        }
    }

    private static void moveShark(int[] shark) {
        for (int i = 1; i < 4; i++) {
            int nx = shark[0] + dx[shark[2]] * i;
            int ny = shark[1] + dy[shark[2]] * i;

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == EMPTY) continue;

            int eatFish = map[nx][ny];
            int dir = fishes[map[nx][ny]].dir;

            map[shark[0]][shark[1]] = EMPTY;
            map[nx][ny] = SHARK;
            fishes[eatFish].isAlive = false;

            DFS(new int[]{nx, ny, dir, shark[3] + eatFish});

            fishes[eatFish].isAlive = true;
            map[shark[0]][shark[1]] = SHARK;
            map[nx][ny] = eatFish;
        }
    }

    private static void moveFishes() {
        for (int i = 1; i < 17; i++) {
            if(!fishes[i].isAlive) continue;

            int cnt = 0;
            int dir = fishes[i].dir;
            int nx = 0, ny = 0;

            while (cnt < 8) {
                nx = fishes[i].x + dx[dir];
                ny = fishes[i].y + dy[dir];

                if (nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == SHARK) {
                    dir = dir + 1 == 9 ? dir = 1 : dir + 1;
                    fishes[i].dir = dir;
                    cnt += 1;
                    continue;
                }

                if (map[nx][ny] == EMPTY) {
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
