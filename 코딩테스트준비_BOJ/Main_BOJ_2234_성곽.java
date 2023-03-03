import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2234_성곽 {
    static class Room{
        boolean[] close;

        public Room() {
            close = new boolean[4];
        }
    }

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int N, M;
    static Room[][] rooms;
    static int[][] roomsNumber;
    static int cnt, beforeMax, max;
    static boolean[][] isVisited;
    static HashMap<Integer, Set<Integer>> side;
    static List<Integer> space;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        rooms = new Room[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                rooms[i][j] = new Room();

                int n = Integer.parseInt(st.nextToken());
                String t = String.format("%04d", Integer.parseInt(Integer.toBinaryString(n)));


                for (int k = 3; k >= 0; k--) {
                    if(t.charAt(k) == '1') rooms[i][j].close[3 - k] = true;
                }
            }
        }

        cnt = 0;
        beforeMax = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        space = new ArrayList<>();
        side = new HashMap<>();
        roomsNumber = new int[N][M];
        isVisited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j]) {
                    cnt += 1;
                    beforeMax = Math.max(beforeMax, findRoom(i, j));
                }
            }
        }

        System.out.println(cnt);
        System.out.println(beforeMax);

        for (int i = 1; i <= cnt; i++) {
            if (side.get(i) != null) {
                for (int j : side.get(i)) {
                    max = Math.max(space.get(i - 1) + space.get(j - 1), max);
                }
            }
        }
        System.out.println(max);

    }

    private static int findRoom(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        q.add(new int[]{x, y});
        roomsNumber[x][y] = cnt;
        isVisited[x][y] = true;

        int size = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M<= ny) continue;
                if(isVisited[nx][ny] && roomsNumber[nx][ny] != cnt){
                    set.add(roomsNumber[nx][ny]);
                    continue;
                }
                if(rooms[now[0]][now[1]].close[d]) continue;
                if(isVisited[nx][ny]) continue;


                roomsNumber[nx][ny] = cnt;
                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                size += 1;
            }
        }

        space.add(size);
        side.put(cnt, set);
        return size;
    }
}
