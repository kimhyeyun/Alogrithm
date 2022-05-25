import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
05.24 - 25 실패 ;;;
나중에 다시 도전~!!!
* */
public class Main_BOJ_9376_탈옥 {
    static final char BLANK = '.', WALL = '*', PRISONER = '$', DOOR = '#';
    static int testCase, h, w;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static char[][] map;

    static class Prisoner implements Comparable<Prisoner> {
        int x, y, openDoor;

        public Prisoner(int x, int y) {
            this.x = x;
            this.y = y;
            this.openDoor = 0;
        }

        public Prisoner(int x, int y, int openDoor) {
            this.x = x;
            this.y = y;
            this.openDoor = openDoor;
        }

        @Override
        public int compareTo(Prisoner o) {
            return Integer.compare(this.openDoor, o.openDoor);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int prisonerIdx = 0, minOpenDoor;
            int[][] prisonerOne, prisonerTwo, sanggen;
            Prisoner[] prisoner = new Prisoner[2];

            stringTokenizer = new StringTokenizer(br.readLine());
            h = Integer.parseInt(stringTokenizer.nextToken()) + 2;
            w = Integer.parseInt(stringTokenizer.nextToken()) + 2;

            map = new char[h][w];

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i + 1][j + 1] = line.charAt(j);
                    if(map[i+1][j+1] == PRISONER) prisoner[prisonerIdx++] = new Prisoner(i + 1, j + 1);
                }
            }

            prisonerOne = BFS(map, prisoner[0], h, w);
            prisonerTwo = BFS(map, prisoner[1], h, w);
            sanggen = BFS(map, new Prisoner(0, 0), h, w);

            minOpenDoor = getMin(prisonerOne, prisonerTwo, sanggen, map);

            System.out.println(minOpenDoor);
        }
    }

    private static int getMin(int[][] one, int[][] two, int[][] three, char[][] map) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < one.length; i++) {
            for (int j = 0; j < one[i].length; j++) {
                if(map[i][j] == WALL) continue;

                int sum = one[i][j] + two[i][j] + three[i][j];
                if(map[i][j] == DOOR) sum -= 2;

                min = sum < min ? sum : min;
            }
        }
        return min;
    }

    private static int[][] BFS(char[][] map, Prisoner prisoner, int h, int w) {
        PriorityQueue<Prisoner> pq = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[h + 2][w + 2];
        int[][] doorHistory = new int[h + 2][w + 2];

        pq.add(prisoner);
        isVisited[prisoner.x][prisoner.y] = true;

        while (!pq.isEmpty()) {
            Prisoner tmp = pq.poll();
            doorHistory[tmp.x][tmp.y] = tmp.openDoor;

            for (int d = 0; d < 4; d++) {
                int nx = tmp.x + dx[d];
                int ny = tmp.y + dy[d];

                if(nx < 0 || ny < 0 || h + 2 <= nx || w + 2 <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == WALL) continue;

                isVisited[nx][ny] = true;
                pq.add(new Prisoner(nx, ny, map[nx][ny] == DOOR ? tmp.openDoor + 1 : tmp.openDoor));
            }
        }
        return doorHistory;
    }
}
