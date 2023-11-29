import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SK_Planet_First_Half {
    public int solution_bread(String[] bakery_schedule, String current_time, int k) {
        int answer = -1;
        String[] t = current_time.split(":");
        int curTime = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);

        int count = 0;
        for (String schedule : bakery_schedule) {
            String[] s = schedule.split(":| ");

            int time = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);

            if(time < curTime) continue;
            else count += Integer.parseInt(s[2]);

            if(count >= k){
                answer = time - curTime;
                break;
            }
        }
        return answer;
    }

    public int[] solution_fishing(int[] p, int[] b) {

        int countOfGroup = 0;
        for (int i = 0; i < p.length; i++) {
            if(p[i] == -1) countOfGroup += 1;
            else findBoss(p, p[i], i);
        }

        int[] answer = new int[countOfGroup];
        for (int i = 0; i < b.length; i++) {
            int count = 1;
            if(p[b[i]] != -1) answer[i] = 0;
            else{
                for (int j = 0; j < p.length; j++) {
                    if(p[j] == b[i]) count += 1;
                }
                answer[i] = count;
            }
        }
        return answer;
    }

    private void findBoss(int[] p, int boss, int idx) {
        if(p[boss] == -1) p[idx] = boss;
        else findBoss(p, p[boss], idx);
    }

    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    public int[] solution_flowers(String[][] boards) {
        int[] answer = new int[boards.length];
        int idx = 0;

        for (String[] board : boards) {
            char[][] map = new char[board.length][board[0].length()];
            int[] player = new int[2];

            int countOfRoad = 0;
            for (int i = 0; i < board.length; i++) {
                map[i] = board[i].toCharArray();
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == '2') player = new int[]{i, j};
                    else if (map[i][j] == '1') countOfRoad += 1;
                }
            }

            int N = map.length;
            if(planting(map, player[0], player[1], 0, 0,  N, countOfRoad)) answer[idx] = 1;

            idx += 1;
        }
        return answer;
    }

    private boolean planting(char[][] map, int x, int y, int flowerCount, int dir, int N, int countOfRoad) {
        if(countOfRoad == flowerCount) return true;

        char[][] tmp = new char[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, N);
        }

        int nx = x;
        int ny = y;
        int count = 0;

        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || tmp[nx][ny] != '1'){
                nx -= dx[dir];
                ny -= dy[dir];
                break;
            }

            tmp[nx][ny] = '2';
            count += 1;
        }

        if (nx == x && ny == y) {
            for (int d = dir + 1; d < 4; d++) {
                if(planting(tmp, nx, ny, flowerCount + count, d, N, countOfRoad)) return true;
            }
        } else {
            for (int d = 0; d < 4; d++) {
                if(planting(tmp, nx, ny, flowerCount + count, d, N, countOfRoad)) return true;
            }
        }
        return false;
    }

    int answer = 0;
    public int solution_road(int n, int m, int k) {
        if(m / n < 1) return 0;

        DFS(n,m,k,0, 0);

        return answer;
    }

    private void DFS(int n, int m, int k, int cnt, int sum) {
        if (cnt == n) {
            if (sum == m) {
                answer += 1;
            }
            return;
        }

        for (int i = 1; i <= k; i++) {
            DFS(n, m, k, cnt + 1, sum + i);
        }
    }

    @Test
    void test() {
//        assertEquals(solution_bread(new String[]{"09:05 10","12:20 5", "13:25 6", "14:24 5"}, "12:05", 10), 80);
//        assertEquals(solution_bread(new String[]{"12:00 10"}, "12:0", 10), 0);
//        assertEquals(solution_bread(new String[]{"12:00 10"}, "12:0", 11), -1);

//        assertArrayEquals(solution_fishing(new int[]{2, 2, -1, 1, 5, -1, 5}, new int[]{2, 5}), new int[]{4, 3});
//        assertArrayEquals(solution_fishing(new int[]{2, 2, -1, 1, 5, -1, 5, 6, 4, 7, 9}, new int[]{2, 5}), new int[]{4, 7});
//        assertArrayEquals(solution_fishing(new int[]{2, 2, -1, 1, 5, -1, 5}, new int[]{1, 5}), new int[]{0, 3});

        assertArrayEquals(solution_flowers(new String[][]{{"00011", "01111", "21001", "11001", "01111"}}), new int[]{1});

//        assertEquals(solution_road(3, 6, 3), 7);
//        assertEquals(solution_road(10, 6, 5), 0);
    }

}
