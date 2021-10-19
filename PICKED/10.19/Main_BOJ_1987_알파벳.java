import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1987_알파벳 {

    static int R,C,ans;
    static char[][] board;
    static boolean[][] isVisited;
    static boolean[] isVisitedChar;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        ans = 0;
        board = new char[R][C];
        isVisited = new boolean[R][C];
        isVisitedChar = new boolean[26];

        for(int i = 0 ; i < R; i++){
            board[i] = br.readLine().toCharArray();
        }

        isVisited[0][0] = true;
        isVisitedChar[board[0][0] - 'A'] = true;
        DFS(0, 0, 1);

        System.out.println(ans);
    }

    private static void DFS(int r, int c, int cnt) {
        
        ans = ans < cnt ? cnt : ans;

        for(int i = 0 ; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || R <= nr || nc < 0 || C <= nc || isVisited[nr][nc]) continue;

            if(!isVisitedChar[board[nr][nc] - 'A']){
                isVisitedChar[board[nr][nc] - 'A'] = true;
                isVisited[nr][nc] = true;
                DFS(nr, nc, cnt+1);
                isVisitedChar[board[nr][nc] - 'A'] = false;
                isVisited[nr][nc] = false;
            }
        }

        return;
    }
}
