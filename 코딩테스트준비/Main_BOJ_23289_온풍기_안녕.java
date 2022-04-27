import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_23289_온풍기_안녕 {
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    static int R, C, K, W;
    static Room[][] map;
    static List<Heater> heaters;
    static List<Room> testRooms;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Room {
        int temperature;
        boolean[] isWall;

        public Room() {
            this.temperature = 0;
            this.isWall = new boolean[4];
        }
    }
    static class Heater {
        int x, y, dir;

        public Heater(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void wind() {
            int forward = dir;
            int left = (dir + 3) % 4;
            int right = (dir + 1) % 4;

            boolean[][] isVisited = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();

            isVisited[x + dx[dir]][y + dy[dir]] = true;
            q.add(new int[]{x + dx[dir], y + dy[dir], 5});

            while (!q.isEmpty()) {
                int[] now = q.poll();
                if(now[2] == 0) continue;

                map[now[0]][now[1]].temperature += now[2];

                int nx = now[0] + dx[forward];
                int ny = now[1] + dy[forward];

                if(valid(nx,ny) && !isVisited[nx][ny]) {
                    if(!map[now[0]][now[1]].isWall[forward]) {
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
                    if (!map[now[0]][now[1]].isWall[left] && !map[nx][ny].isWall[forward]) {
                        isVisited[nx2][ny2] = true;
                        q.add(new int[]{nx2, ny2, now[2] - 1});
                    }
                }
                nx = now[0] + dx[right];
                ny = now[1] + dy[right];

                nx2 = nx + dx[forward];
                ny2 = ny + dy[forward];

                if(valid(nx, ny) && valid(nx2, ny2) && !isVisited[nx2][ny2]) {
                    if (!map[now[0]][now[1]].isWall[right] && !map[nx][ny].isWall[forward]) {
                        isVisited[nx2][ny2] = true;
                        q.add(new int[]{nx2, ny2, now[2] - 1});
                    }
                }
            }
        }

    }

    static boolean valid(int x, int y) {
        if(x < 0 || y < 0 || R <= x || C <= y) return false;
        else return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        heaters = new ArrayList<>();
        map = new Room[R][C];
        testRooms = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = new Room();

                int num = Integer.parseInt(stringTokenizer.nextToken());
                if(num == 0) continue;
                if (num == 5) {
                    testRooms.add(map[i][j]);
                } else {
                    int dir = getDir(num);
                    heaters.add(new Heater(i, j, dir));
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int t = Integer.parseInt(stringTokenizer.nextToken());
            setWall(x, y, t);
        }

        int chocolate = 0;
        while (chocolate < 101) {
            workedHeater();
//            print("--------------heaterwork-----------------");

            spreadHeat();
//            print("--------------spreadheat----------------");
            decreaceHeat();

            chocolate += 1;

//            print("--------------decreaceheat----------------");

            if (test()) {
                System.out.println(chocolate);
                return;
            }
        }

        System.out.println(101);
    }

    private static void print(String str) {
        System.out.println(str);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j].temperature+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean test() {
        for (Room room : testRooms) {
            if(room.temperature < K) return false;
        }
        return true;
    }

    private static void decreaceHeat() {
        for (int j = 0; j < C - 1; j++) {
            if (map[0][j].temperature > 0) map[0][j].temperature -= 1;
        }
        for (int i = 0; i < R - 1; i++) {
            if(map[i][C-1].temperature > 0) map[i][C - 1].temperature -= 1;
        }
        for (int j = C - 1; j >= 1; j--) {
            if(map[R-1][j].temperature > 0) map[R - 1][j].temperature -= 1;
        }
        for (int i = R - 1; i >= 1; i--) {
            if(map[i][0].temperature > 0) map[i][0].temperature -= 1;
        }
    }

    private static void spreadHeat() {
        int[][] savedHeat = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Room room = map[i][j];
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!valid(nx,ny) || room.isWall[d]) continue;

                    Room next = map[nx][ny];
                    int spread = (room.temperature - next.temperature) / 4;
                    if (spread > 0) {
                        savedHeat[i][j] -= spread;
                        savedHeat[nx][ny] += spread;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j].temperature += savedHeat[i][j];
            }
        }
    }

    private static void setWall(int x, int y, int t) {
        if (t == 0) {
            map[x][y].isWall[UP] = true;
            map[x - 1][y].isWall[DOWN] = true;
        } else {
            map[x][y].isWall[RIGHT] = true;
            map[x][y + 1].isWall[LEFT] = true;
        }
    }

    private static int getDir(int num) {
        if(num == 1) return RIGHT;
        else if(num == 2) return LEFT;
        else if(num == 3) return UP;
        else return DOWN;
    }

    private static void workedHeater() {
        for (Heater heater : heaters) {
            heater.wind();
//            print("--------------heaterwork-----------------");
        }
    }
}
