import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_23290_마법사_상어와_복제 {

    static class Fish {
        int x, y;
        int dir;

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
                int nd = (dir - d + 8) % 8;
                int nx = x + fishOfDx[nd];
                int ny = y + fishOfDy[nd];

                if (!canMove(nx, ny)) continue;

                x = nx;
                y = ny;
                dir = nd;
                return;
            }
        }

        private boolean canMove(int x, int y) {
            return valid(x, y) && noScent(x, y) && noShark(x, y);
        }

        private boolean noShark(int x, int y) {
            return shark.x != x || shark.y != y;
        }

        private boolean noScent(int x, int y) {
            return map[x][y].scent == 0;
        }
    }

    static class Shark {
        int x, y;
        List<int[]> orders;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            makeOrders();
        }

        private void makeOrders() {
            orders = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        orders.add(new int[]{i, j, k});
                    }
                }
            }
        }

        public int[] findNextMove() {
            int maxCountOfFishes = -1;
            int[] maxOrder = new int[3];

            int nx = x, ny = y;
            for (int[] order : orders) {
                int countOfFish = 0;
                boolean flag = true;
                boolean[][] isVisited = new boolean[4][4];

                nx = x;
                ny = y;

                for (int d = 0; d < 3; d++) {
                    nx += sharkOfDx[order[d]];
                    ny += sharkOfDy[order[d]];

                    if (valid(nx, ny)) {
                        if (!isVisited[nx][ny]) {
                            countOfFish += map[nx][ny].fishes.size();
                            isVisited[nx][ny] = true;
                        }
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    if (maxCountOfFishes < countOfFish) {
                        maxCountOfFishes = countOfFish;
                        maxOrder = order;
                    }
                }
            }
            return maxOrder;
        }
    }

    static class Square {
        List<Fish> fishes, copies;
        int scent;

        public Square() {
            fishes = new ArrayList<>();
            copies = new ArrayList<>();
            scent = 0;
        }

        public void copy() {
            for (Fish fish : fishes) {
                copies.add(new Fish(fish));
            }
        }

        public void completeCopy() {
            fishes.addAll(copies);
            copies = new ArrayList<>();
        }
    }



    static int M, S;
    static Shark shark;
    static Square[][] map;

    static final int[] fishOfDx = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] fishOfDy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] sharkOfDx = {-1, 0, 1, 0};
    static final int[] sharkOfDy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map = new Square[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Square();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            Fish fish = new Fish(x, y, d);
            map[x][y].fishes.add(fish);
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        shark = new Shark(x, y);

        while (S-- > 0) {
            copyFishes();
            moveFishes();
            moveShark();
            reduceScent();
            completeCopyMagic();
        }

        System.out.println(countOfFishes());
    }

    private static int countOfFishes() {
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                answer += map[i][j].fishes.size();
            }
        }
        return answer;
    }

    private static void completeCopyMagic() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j].completeCopy();
            }
        }
    }

    private static void reduceScent() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].scent > 0) {
                    map[i][j].scent -= 1;
                }
            }
        }
    }

    private static void moveShark() {
        int[] howToMove = shark.findNextMove();

        int nx = shark.x;
        int ny = shark.y;

        for (int i = 0; i < 3; i++) {
            nx += sharkOfDx[howToMove[i]];
            ny += sharkOfDy[howToMove[i]];

            if (map[nx][ny].fishes.size() > 0) {
                map[nx][ny].scent = 3;
                map[nx][ny].fishes = new ArrayList<>();
            }
        }

        shark.x = nx;
        shark.y = ny;
    }

    private static void moveFishes() {
        List<Fish> movedFishes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Fish fish : map[i][j].fishes) {
                    fish.move();
                    movedFishes.add(fish);
                }
                map[i][j].fishes = new ArrayList<>();
            }
        }

        for (Fish fish : movedFishes) {
            int x = fish.x;
            int y = fish.y;

            map[x][y].fishes.add(fish);
        }
    }

    private static void copyFishes() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j].copy();
            }
        }
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}
