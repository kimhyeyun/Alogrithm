import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_20058_마법사_상어와_파이어스톰 {
    static int N, Q, largestIceBerg, sumOfIce;
    static int[][] map;
    static int[] L;

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);

        map = new int[N][N];
        L = new int[Q];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            map = divdeAndRotate(L[i]);
//            printMap();
            map = meltIce();
//            printMap();
        }

        findLargestIceBerg();

        System.out.println(sumOfIce);
        System.out.println(largestIceBerg);
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void findLargestIceBerg() {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        largestIceBerg = 0;
        sumOfIce = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] <= 0 || isVisited[i][j]) continue;

                q.add(new int[]{i, j});
                isVisited[i][j] = true;
                sumOfIce += map[i][j];

                int count = 1;
                while (!q.isEmpty()) {
                    int[] now = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = now[0] + dx[d];
                        int ny = now[1] + dy[d];

                        if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                        if(map[nx][ny] <= 0 || isVisited[nx][ny]) continue;

                        isVisited[nx][ny] = true;
                        q.add(new int[]{nx, ny});

                        count += 1;
                        sumOfIce += map[nx][ny];
                    }
                }

                largestIceBerg = Math.max(largestIceBerg, count);
            }
        }
    }

    private static int[][] meltIce() {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) continue;
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                    if(map[nx][ny] <= 0) continue;

                    count += 1;
                }

                if(count < 3) tmp[i][j] -= 1;
            }
        }
        return tmp;
    }

    private static int[][] divdeAndRotate(int size) {
        int[][] tmp = new int[N][N];
        size = (int) Math.pow(2, size);

        for (int i = 0; i < N; i += size) {
            for (int j = 0; j < N; j += size) {
                rotateSubMap(i, j, size, tmp);
            }
        }
        return tmp;
    }

    private static void rotateSubMap(int x, int y, int size, int[][] tmp) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[x + j][y + size - i - 1] = map[i + x][j + y];
            }
        }
    }
}
