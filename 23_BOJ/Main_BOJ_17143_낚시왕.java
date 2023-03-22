import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕 {
    static class Shark {
        int x, y;
        int speed, dir, size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    static int R, C, M;
    static Shark[] sharks;
    static int[][] map;
    static final int[] dx = {0, -1, 1, 0, 0};
    static final int[] dy = {0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println(0);
            return;
        }

        map = new int[R][C];
        sharks = new Shark[M + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(x, y, s, d, z);
            map[x][y] = i;
        }

        int answer = 0;
        for (int index = 0; index < C; index++) {
            answer += fishing(index);
//            printMap();
            map = moveSharks();
//            printMap();
        }

        System.out.println(answer);
    }

    private static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] moveSharks() {
        int[][] tmp = new int[R][C];

        for (int index = 1; index <= M; index++) {
            if(sharks[index] == null) continue;

            int s = sharks[index].speed;
            if(sharks[index].dir == 1 || sharks[index].dir == 2){
                s %= (R - 1) * 2;
            }else{
                s %= (C - 1) * 2;
            }

            int nx = sharks[index].x;
            int ny = sharks[index].y;
            for (int i = 0; i < s; i++) {
                nx += dx[sharks[index].dir];
                ny += dy[sharks[index].dir];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) {
                    nx -= (dx[sharks[index].dir] * 2);
                    ny -= (dy[sharks[index].dir] * 2);

                    if (sharks[index].dir == 1) {
                        sharks[index].dir = 2;
                    } else if (sharks[index].dir == 2) {
                        sharks[index].dir = 1;
                    } else if (sharks[index].dir == 3) {
                        sharks[index].dir = 4;
                    } else {
                        sharks[index].dir = 3;
                    }
                }
            }

            if (tmp[nx][ny] == 0){
                tmp[nx][ny] = index;

                sharks[index].x = nx;
                sharks[index].y = ny;
            } else {
                int n = tmp[nx][ny];
                if (sharks[n].size < sharks[index].size) {
                    tmp[nx][ny] = index;
                    sharks[n] = null;

                    sharks[index].x = nx;
                    sharks[index].y = ny;
                } else {
                    sharks[index] = null;
                }
            }
        }
        return tmp;
    }

    private static int fishing(int index) {
        for (int i = 0; i < R; i++) {
            if (map[i][index] > 0) {
                int num = map[i][index];
                int size = sharks[num].size;

                map[i][index] = 0;
                sharks[num] = null;

                return size;
            }
        }
        return 0;
    }
}
