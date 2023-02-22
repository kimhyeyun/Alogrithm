import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3109_빵집 {
    static int R, C, answer;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        answer = 0;
        for (int i = 0; i < R; i++) {
            if(isPossible(i,0)) answer += 1;
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int x, int y) {
        map[x][y] = '-';

        if(y == C - 1) return true;
        if (x > 0 && map[x - 1][y + 1] == '.') {
            if (isPossible(x - 1, y + 1)) return true;
        }
        if (map[x][y + 1] == '.') {
            if (isPossible(x, y + 1)) return true;
        }
        if (x + 1 < R && map[x + 1][y + 1] == '.') {
            if (isPossible(x + 1, y + 1)) return true;
        }
        return false;
    }
}
