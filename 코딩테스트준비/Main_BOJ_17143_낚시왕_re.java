import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕_re {
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
    static int R, C, M, ans = 0;
    static Shark[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int z = Integer.parseInt(stringTokenizer.nextToken());

            map[x][y] = new Shark(x, y, s, d, z);
        }

        for (int i = 0; i < C; i++) {
            fishing(i);
            map = moveShark();
        }
        System.out.println(ans);
    }

    private static Shark[][] moveShark() {
        Shark[][] tmp = new Shark[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == null) continue;

                Shark now = map[i][j];
                int speed = now.speed;

                if(now.dir == 0 || now.dir == 1) speed %= (R - 1) * 2;
                else speed %= (C - 1) * 2;

                for (int s = 0; s < speed; s++) {
                    int nx = now.x + dx[now.dir];
                    int ny = now.y + dy[now.dir];

                    if (nx < 0 || ny < 0 || R <= nx || C <= ny) {
                        now.x -= dx[now.dir];
                        now.y -= dy[now.dir];

                        if(now.dir == 0) now.dir = 1;
                        else if(now.dir == 1) now.dir = 0;
                        else if(now.dir == 2) now.dir = 3;
                        else now.dir = 2;

                        continue;
                    }

                    now.x = nx;
                    now.y = ny;
                }
                if(tmp[now.x][now.y] != null && tmp[now.x][now.y].size > now.size) continue;

                tmp[now.x][now.y] = now;
            }
        }
        return tmp;
    }

    private static void fishing(int col) {
        for (int i = 0; i < R; i++) {
            if(map[i][col] == null) continue;

            ans += map[i][col].size;
            map[i][col] = null;
            return;
        }
    }
}
