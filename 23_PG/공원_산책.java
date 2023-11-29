import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class 공원_산책 {

    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};

        int H = park.length;
        int W = park[0].length();

        int[][] map = new int[H][W];
        int[] now = new int[2];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (park[i].charAt(j) == 'S') {
                    now = new int[]{i, j};
                } else if (park[i].charAt(j) == 'X') {
                    map[i][j] = -2;
                }
            }
        }

        int dir = -1;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (String route : routes) {
            String[] str = route.split(" ");

            if (str[0].equals("N")) {
                dir = 0;
            } else if (str[0].equals("S")) {
                dir = 1;
            } else if (str[0].equals("W")) {
                dir = 2;
            } else {
                dir = 3;
            }

            int n = Integer.parseInt(str[1]);
            int nx = now[0];
            int ny = now[1];

            boolean flag = false;
            for (int i = 0; i < n; i++) {
                nx += dx[dir];
                ny += dy[dir];

                if (nx < 0 || ny < 0 || H <= nx || W <= ny || map[nx][ny] == -2) {
                    flag = true;
                    break;
                }
            }

            if(flag) continue;
            now = new int[]{nx, ny};
        }

        return now;
    }

    @Test
    void test() {
//        assertArrayEquals(solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"}), new int[]{2, 1});
        assertArrayEquals(solution(new String[]{"SOO", "OXX", "OOO"}, new String[]{"E 2", "S 2", "W 1"}), new int[]{0, 1});
    }
}
