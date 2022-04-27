import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main_BOJ_23290_마법사_상어와_복제 {
    static int M, S;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkDx = {-1, 0, 1, 0};
    static int[] sharkDy = {0, -1, 0, 1};
    static Shark shark;
    static Room[][] rooms;

    static class Fish {
        int x, y, dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Fish(Fish o) {
            this.x = o.x;
            this.y = o.y;
            this.dir = o.dir;
        }

        public void move() {
            for (int d = 0; d < 8; d++) {
                int nd = (dir + 8 - d) % 8;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (canGo(nx, ny)) {
                    this.x = nx;
                    this.y = ny;
                    this.dir = nd;
                    return;
                }
            }
        }

        private boolean canGo(int x, int y) {
            if(valid(x,y) && noShark(x,y) && noScent(x,y)) return true;
            return false;
        }

        private boolean noScent(int x, int y) {
            if(rooms[x][y].scent == 0) return true;
            return false;
        }

        private boolean noShark(int x, int y) {
            if(x == shark.x && y == shark.y) return false;
            return true;
        }
    }

    static class Shark {
        List<int[]> orders = new ArrayList<>();
        int x, y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            makeOrder();
        }

        public void makeOrder() {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        orders.add(new int[]{i, j, k});
                    }
                }
            }
        }

        public int[] findNextMove() {
            int maxFish = -1;
            int[] maxOrder = new int[3];

            int nx = x, ny = y;
            for (int[] order : orders) {
                int fish = 0;
                boolean flag = true;
                boolean[][] isVisited = new boolean[4][4];

                nx = x;
                ny = y;

                for (int d = 0; d < 3; d++) {
                    nx += sharkDx[order[d]];
                    ny += sharkDy[order[d]];

                    if (valid(nx, ny)) {
                        if (!isVisited[nx][ny]) fish += rooms[nx][ny].fishes.size();

                        isVisited[nx][ny] = true;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    if (maxFish < fish) {
                        maxFish = fish;
                        maxOrder = order;
                    }
                }
            }
            return maxOrder;
        }
    }

    static class Room {
        List<Fish> fishes = new ArrayList<>();
        List<Fish> copies = new ArrayList<>();
        int scent = 0;

        public void copy() {
            for (Fish fish : fishes) {
                copies.add(new Fish(fish));
            }
        }

        public void reduceScent() {
            if (this.scent > 0) scent -= 1;
        }

        public void completeCopy() {
            for (Fish fish : copies) {
                fishes.add(fish);
            }
            copies = new ArrayList<>();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        M = Integer.parseInt(stringTokenizer.nextToken());
        S = Integer.parseInt(stringTokenizer.nextToken());

        rooms = new Room[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rooms[i][j] = new Room();
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            Fish fish = new Fish(x, y, d);
            rooms[x][y].fishes.add(fish);
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        shark = new Shark(x, y);

        while (S-- > 0) {
            copyFishes();
            moveFishes();
            moveShark();
            reduceScent();
            completeCopyFishes();
        }
        System.out.println(countAllFish());
    }

    private static int countAllFish() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cnt += rooms[i][j].fishes.size();
            }
        }
        return cnt;
    }

    private static void completeCopyFishes() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                rooms[i][j].completeCopy();
            }
    }

    private static void reduceScent() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rooms[i][j].reduceScent();
            }
        }
    }

    private static void moveShark() {
        int[] howToMove = shark.findNextMove();

        int nx = shark.x;
        int ny = shark.y;
        for (int i = 0; i < 3; i++) {
            nx += sharkDx[howToMove[i]];
            ny += sharkDy[howToMove[i]];

            if (rooms[nx][ny].fishes.size() > 0) {
                rooms[nx][ny].scent = 3;
                rooms[nx][ny].fishes = new ArrayList<>();
            }
        }
        shark.x = nx;
        shark.y = ny;
    }

    private static void moveFishes() {
        List<Fish> saveFish = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Fish fish : rooms[i][j].fishes) {
                    fish.move();
                    saveFish.add(fish);
                }
                rooms[i][j].fishes = new ArrayList<>();
            }
        }

        for (Fish fish : saveFish) {
            int x = fish.x;
            int y = fish.y;
            rooms[x][y].fishes.add(fish);
        }
    }

    private static void copyFishes() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rooms[i][j].copy();
            }
        }
    }

    public static boolean valid(int x, int y) {
        if(x < 0 || y < 0 || 4 <= x || 4 <= y) return false;
        return true;
    }
}
