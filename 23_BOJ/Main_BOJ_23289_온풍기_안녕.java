import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_23289_온풍기_안녕 {
    static class Square {
        int temperature;
        boolean[] isWall;

        public Square() {
            this.temperature = 0;
            this.isWall = new boolean[4];
        }
    }

    static class Heater {
        int x, y;
        int dir;

        public Heater(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void wind() {
            int forward = dir;
            int left = (dir + 3) % 4;
            int right = (dir + 1) % 4;

            boolean[][] isVisited = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();

            isVisited[x + dx[dir]][y + dy[dir]] = true;
            q.add(new int[]{x + dx[dir], y + dy[dir], 5});

            while (!q.isEmpty()) {
                int[] now = q.poll();
                if(now[2] == 0) continue;

                room[now[0]][now[1]].temperature += now[2];

                int nx = now[0] + dx[forward];
                int ny = now[1] + dy[forward];

                if(valid(nx,ny) && !isVisited[nx][ny]) {
                    if (!room[now[0]][now[1]].isWall[forward]) {
                        isVisited[nx][ny] = true;
                        q.add(new int[]{nx, ny, now[2] - 1});
                    }
                }

                int nx2, ny2;

                nx = now[0] + dx[left];
                ny = now[1] + dy[left];

                nx2 = nx + dx[forward];
                ny2 = ny + dy[forward];

                if(valid(nx, ny) && valid(nx2, ny2) && !isVisited[nx2][ny2]) {
                    if (!room[now[0]][now[1]].isWall[left] && !room[nx][ny].isWall[forward]) {
                        isVisited[nx2][ny2] = true;
                        q.add(new int[]{nx2, ny2, now[2] - 1});
                    }
                }

                nx = now[0] + dx[right];
                ny = now[1] + dy[right];

                nx2 = nx + dx[forward];
                ny2 = ny + dy[forward];

                if(valid(nx, ny) && valid(nx2, ny2) && !isVisited[nx2][ny2]) {
                    if (!room[now[0]][now[1]].isWall[right] && !room[nx][ny].isWall[forward]) {
                        isVisited[nx2][ny2] = true;
                        q.add(new int[]{nx2, ny2, now[2] - 1});
                    }
                }
            }
        }
    }

    static int N, M, K, W;
    static Square[][] room;
    static List<Heater> heaters;
    static List<Square> testSquares;

    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        room = new Square[N][M];
        heaters = new ArrayList<>();
        testSquares = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = new Square();

                int n = Integer.parseInt(st.nextToken());
                if(n == 0) continue;
                if (n == 5) {
                    testSquares.add(room[i][j]);
                } else {
                    int d = getDir(n);
                    heaters.add(new Heater(i, j, d));
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            setWall(x, y, t);
        }

        int answer = 0;
        while (answer < 101) {
            workHeaters();
//            print("--------------workHeaters-----------------");

            spreadHeats();
//            print("--------------spreadHeats-----------------");

            decreaseHeats();
//            print("--------------decreaseHeats-----------------");

            answer += 1;

            if (checkTestSquares()) {
                System.out.println(answer);
                return;
            }
        }
        System.out.println(101);
    }

    private static void print(String str) {
        System.out.println(str);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(room[i][j].temperature+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean checkTestSquares() {
        for (Square square : testSquares) {
            if(square.temperature < K) return false;
        }
        return true;
    }

    private static void decreaseHeats() {
        for (int j = 0; j < M - 1; j++) {
            if(room[0][j].temperature > 0) room[0][j].temperature -= 1;
        }

        for (int i = 0; i < N - 1; i++) {
            if(room[i][M - 1].temperature > 0) room[i][M - 1].temperature -= 1;
        }

        for (int j = M - 1; j >= 1; j--) {
            if(room[N - 1][j].temperature > 0) room[N - 1][j].temperature -= 1;
        }

        for (int i = N - 1; i >= 1; i--) {
            if(room[i][0].temperature > 0) room[i][0].temperature -= 1;
        }
    }

    private static void spreadHeats() {
        int[][] savedHeat = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Square now = room[i][j];

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!valid(nx, ny) || now.isWall[d]) continue;

                    Square next = room[nx][ny];
                    int spread = (now.temperature - next.temperature) / 4;
                    if (spread > 0) {
                        savedHeat[i][j] -= spread;
                        savedHeat[nx][ny] += spread;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                room[i][j].temperature += savedHeat[i][j];
            }
        }
    }

    private static void workHeaters() {
        for (Heater heater : heaters) {
            heater.wind();
        }
    }

    private static void setWall(int x, int y, int type) {
        if (type == 0) {
            room[x][y].isWall[UP] = true;
            room[x - 1][y].isWall[DOWN] = true;
        } else {
            room[x][y].isWall[RIGHT] = true;
            room[x][y + 1].isWall[LEFT] = true;
        }

    }

    private static int getDir(int n) {
        if(n == 1) return RIGHT;
        else if(n == 2) return LEFT;
        else if(n == 3) return UP;
        else return DOWN;
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && y >= 0 && N > x && M > y;
    }
}
