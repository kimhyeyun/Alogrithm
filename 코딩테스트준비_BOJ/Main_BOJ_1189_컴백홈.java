import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1189_컴백홈 {
    static int R, C, K;
    static int answer;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        isVisited = new boolean[R][C];
        isVisited[R - 1][0] = true;

        answer = 0;

        DFS(R - 1, 0, 1);

        System.out.println(answer);
    }

    private static void DFS(int x, int y, int cnt) {
        if (x == 0 && y == C - 1) {
            if(cnt == K) answer += 1;
            return;
        }


        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
            if(isVisited[nx][ny] || map[nx][ny] == 'T') continue;

            isVisited[nx][ny] = true;
            DFS(nx, ny, cnt + 1);
            isVisited[nx][ny] = false;
        }
    }
}
