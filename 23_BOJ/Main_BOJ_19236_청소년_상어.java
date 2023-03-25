import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19236_청소년_상어 {
    static class Fish {
        int index;
        int x, y;
        int dir;
        boolean isAlive;

        public Fish(int index, int x, int y, int dir, boolean isAlive) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static int answer;
    static int[][] map;
    static Fish[] fishes;

    static final int SHARK = -1, EMPTY = 0;
    static final int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[4][4];
        fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int index = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                fishes[index] = new Fish(index, i, j, dir, true);
                map[i][j] = index;
            }
        }

        answer = Integer.MIN_VALUE;

        int indexOfEatFish = map[0][0];
        fishes[indexOfEatFish].isAlive = false;
        map[0][0] = SHARK;

        DFS(new int[]{0, 0, fishes[indexOfEatFish].dir, indexOfEatFish});

        System.out.println(answer);
    }

    private static void DFS(int[] shark) {
        answer = Math.max(answer, shark[3]);

        int[][] tmpOfMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, tmpOfMap[i], 0, 4);
        }

        Fish[] tmpOfFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            tmpOfFishes[i] = new Fish(fishes[i].index, fishes[i].x, fishes[i].y, fishes[i].dir, fishes[i].isAlive);
        }

        moveFishes();
        moveShark(shark);

        for (int i = 0; i < 4; i++) {
            System.arraycopy(tmpOfMap[i], 0, map[i], 0, 4);
        }

        for (int i = 1; i <= 16; i++) {
            fishes[i] = new Fish(tmpOfFishes[i].index, tmpOfFishes[i].x, tmpOfFishes[i].y, tmpOfFishes[i].dir, tmpOfFishes[i].isAlive);
        }
    }

    private static void moveShark(int[] shark) {
        for (int i = 1; i <= 4; i++) {
            int nx = shark[0] + dx[shark[2]] * i;
            int ny = shark[1] + dy[shark[2]] * i;

            if(nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == EMPTY) continue;

            int indexOfEatFish = map[nx][ny];
            int dir = fishes[indexOfEatFish].dir;

            map[shark[0]][shark[1]] = EMPTY;
            map[nx][ny] = SHARK;
            fishes[indexOfEatFish].isAlive = false;

            DFS(new int[]{nx, ny, dir, shark[3] + indexOfEatFish});

            fishes[indexOfEatFish].isAlive = true;
            map[shark[0]][shark[1]] = SHARK;
            map[nx][ny] = indexOfEatFish;
        }
    }

    private static void moveFishes() {
        for (int i = 1; i <= 16; i++) {
            if(!fishes[i].isAlive) continue;

            int count = 0;
            int dir = fishes[i].dir;
            int nx = 0, ny = 0;

            while (count < 8) {
                nx = fishes[i].x + dx[dir];
                ny = fishes[i].y + dy[dir];

                if (nx < 0 || ny < 0 || 4 <= nx || 4 <= ny || map[nx][ny] == SHARK) {
                    dir = dir + 1 == 9 ? 1 : dir + 1;
                    fishes[i].dir = dir;
                    count += 1;
                    continue;
                }

                if (map[nx][ny] == EMPTY) {
                    map[nx][ny] = fishes[i].index;
                    map[fishes[i].x][fishes[i].y] = EMPTY;

                    fishes[i].x = nx;
                    fishes[i].y = ny;
                } else {
                    int indexOfChangeFish = map[nx][ny];

                    fishes[indexOfChangeFish].x = fishes[i].x;
                    fishes[indexOfChangeFish].y = fishes[i].y;
                    map[fishes[i].x][fishes[i].y] = indexOfChangeFish;

                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    map[nx][ny] = i;
                }

                break;
            }
        }
    }
}
