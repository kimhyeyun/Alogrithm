import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3109_빵집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            if(isPossible(i, 0, map)) answer += 1;
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int x, int y, char[][] map) {
        int N = map.length;
        int M = map[0].length;

        map[x][y] = '-';
        if(y == M - 1) return true;
        if (0 < x && map[x - 1][y + 1] == '.') {
            if(isPossible(x - 1,y + 1, map)) return true;
        }
        if (map[x][y + 1] == '.') {
            if(isPossible(x, y + 1, map)) return true;
        }
        if (x < N - 1 && map[x + 1][y + 1] == '.') {
            if(isPossible(x + 1, y + 1, map)) return true;
        }
        return false;
    }
}
