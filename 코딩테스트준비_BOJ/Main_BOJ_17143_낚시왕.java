import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17143_낚시왕 {

    static class Shark {
        int r, c, speed, dir, size;

        public Shark(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", speed=" + speed +
                    ", dir=" + dir +
                    ", size=" + size +
                    '}';
        }
    }

    static int R, C, M, answer = 0;
    static Shark[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int z = Integer.parseInt(stringTokenizer.nextToken());

            map[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d, z);
        }

        solution();
        System.out.println(answer);
    }

    private static void solution() {
        for (int i = 0; i < C; i++) {
//            System.out.println(i);
            fishing(i);
            map = moveSharks();
/*            for (int k = 0; k < R; k++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[k][j] == null ? -1+" " : map[k][j].size+" ");
                }
                System.out.println();
            }*/
        }
    }

    private static Shark[][] moveSharks() {
        Shark[][] tmp = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == null) continue;

                Shark now = map[i][j];
                int speed = now.speed;
                if(now.dir == 0 || now.dir == 1) speed %= (R - 1) * 2;
                else speed %= (C - 1) * 2;

                for (int s = 0; s < speed; s++) {
                    int nr = now.r + dr[now.dir];
                    int nc = now.c + dc[now.dir];

                    if (nr < 0 || nc < 0 || R <= nr || C <= nc) {
                        now.r -= dr[now.dir];
                        now.c -= dc[now.dir];

                        if(now.dir == 0) now.dir = 1;
                        else if(now.dir == 1) now.dir = 0;
                        else if(now.dir == 2) now.dir = 3;
                        else now.dir = 2;
                        continue;
                    }

                    now.r = nr;
                    now.c = nc;
                }

                if(tmp[now.r][now.c] != null && tmp[now.r][now.c].size > now.size) continue;

                tmp[now.r][now.c] = now;
//                System.out.println(now.toString());
            }
        }
        /*for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmp[i][j] != null) {
                    System.out.println("i: " + i + " j: " + j);
                    System.out.println(tmp[i][j].toString());
                }
            }
        }*/

        return tmp;
    }


    private static void fishing(int col) {
        for (int i = 0; i < R; i++) {
            if(map[i][col] == null) continue;

            answer += map[i][col].size;
//            System.out.println(map[i][col].size);
            map[i][col] = null;
            return;
        }
    }
}
