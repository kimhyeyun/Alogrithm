import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_17143_낚시왕 {
    static class Shark {
        int x, y, speed, dir, size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    static int R, C, M, answer;
    static Shark[][] space;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        space = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            space[x][y] = new Shark(x, y, s, d, z);

        }

        for (int i = 0; i < C; i++) {
            fishing(i);
            space = moveSharks();
        }

        System.out.println(answer);
    }

    private static Shark[][] moveSharks() {
        Shark[][] tmp = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(space[i][j] == null) continue;

                Shark shark = space[i][j];
                int x = shark.x;
                int y = shark.y;

                if(shark.dir == 1  || shark.dir == 2) shark.speed %= (R - 1) * 2;
                else shark.speed %= (C - 1) * 2;

                for (int s = 0; s < shark.speed; s++) {
                    x += dx[shark.dir];
                    y += dy[shark.dir];

                    if (x < 0 || y < 0 || R <= x || C <= y) {
                        if(shark.dir == 1) shark.dir = 2;
                        else if(shark.dir == 2) shark.dir = 1;
                        else if(shark.dir == 3) shark.dir = 4;
                        else shark.dir = 3;

                        x += (dx[shark.dir] * 2);
                        y += (dy[shark.dir] * 2);
                    }
                }
                if (tmp[x][y] != null) {
                    if(tmp[x][y].size > shark.size) continue;
                }

                tmp[x][y] = new Shark(x, y, shark.speed, shark.dir, shark.size);
            }
        }
        return tmp;
    }

    private static void fishing(int y) {
        for (int x = 0; x < R; x++) {
            if(space[x][y] == null) continue;

            answer += space[x][y].size;
            space[x][y] = null;
            return;
        }
    }
}
