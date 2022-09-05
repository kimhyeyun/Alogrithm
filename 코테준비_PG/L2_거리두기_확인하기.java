import java.util.LinkedList;
import java.util.Queue;

public class L2_거리두기_확인하기 {
    static boolean isAvailable;
    static char[][] place;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            isAvailable = true;
            place = new char[5][5];
            for (int j = 0; j < 5; j++) {
                place[j] = places[i][j].toCharArray();
            }

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (place[x][y] == 'P') {
                        isVisited = new boolean[5][5];
                        DFS(0, x, y);
                        if(!isAvailable) break;
                    }
                }
                if(!isAvailable) break;
            }
            if(!isAvailable) answer[i] = 0;
            else answer[i] = 1;

        }
        return answer;
    }

    private static void DFS(int currentDepth, int x, int y) {

        if(currentDepth >= 2) return;

        isVisited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || 5 <= nx || 5 <= ny || isVisited[nx][ny]) continue;

            if(place[nx][ny] == 'O') DFS(currentDepth + 1, nx, ny);
            else if (place[nx][ny] == 'P') {
                isAvailable = false;
                return;
            } else if(place[nx][ny] == 'X') continue;
        }
    }


    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int[] answer = solution(places);

        for (int a : answer) {
            System.out.println(a);
        }
    }
}
