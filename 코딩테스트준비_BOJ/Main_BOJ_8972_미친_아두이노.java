import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_8972_미친_아두이노 {
    static int R, C, cnt;
    static int[][] map;
    static List<int[]> crazyList;
    static int[] my = new int[2];
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        crazyList = new ArrayList<>();
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == 'R') {
                    crazyList.add(new int[]{i, j});
                    map[i][j] = -1;
                }
                if(s.charAt(j) == 'I') my = new int[]{i, j};
            }
        }

        String moveStr = br.readLine();


        if (!play(moveStr)) {
            sb.append("kraj ").append(cnt);
        } else {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j] == -1) sb.append("R");
                    else if(i == my[0] && j == my[1]) sb.append("I");
                    else sb.append(".");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean play(String moveStr) {
        for (int i = 0; i < moveStr.length(); i++) {
            cnt = i + 1;
            int dir = (moveStr.charAt(i) - '0');

            my[0] += dx[dir];
            my[1] += dy[dir];

            if(map[my[0]][my[1]] == -1) return false;
            if(meetCrazyArduino()) return false;
        }
        return true;
    }

    private static boolean meetCrazyArduino() {

        for (int[] arduino : crazyList) {
            map[arduino[0]][arduino[1]] += 1;
            int min = Integer.MAX_VALUE;
            int dir = 0;
            int dist;
            for (int d = 1; d < 10; d++) {
                dist = Math.abs(my[0] - (arduino[0] + dx[d])) + Math.abs(my[1] - (arduino[1] + dy[d]));
                min = dist < min ? dist : min;
                if(dist == min) dir = d;
            }

            arduino[0] += dx[dir];
            arduino[1] += dy[dir];
            map[arduino[0]][arduino[1]] -= 1;

            if(my[0] == arduino[0] && my[1] == arduino[1]) return true;
        }

        crazyList.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] < -1) map[i][j] = 0;
                else if(map[i][j] == -1) crazyList.add(new int[]{i, j});
            }
        }

        return false;
    }
}
