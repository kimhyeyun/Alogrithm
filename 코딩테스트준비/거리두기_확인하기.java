import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 거리두기_확인하기 {

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            char[][] place = new char[5][5];
            for (int j = 0; j < 5; j++) {
                place[j] = places[i][j].toCharArray();
            }
            if(!checkRoom(place)) answer[i] = 0;
            else answer[i] = 1;

        }
        return answer;
    }

    private static boolean checkRoom(char[][] place) {
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                if(place[i][j] == 'P')
                    if(!BFS(place, i, j)) return false;
            }
        }
        return true;
    }

    private static boolean BFS(char[][] place, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisitied = new boolean[5][5];

        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            isVisitied[now[0]][now[1]] = true;

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                int dis = Math.abs(x - nx) + Math.abs(y - ny);

                if(nx < 0 || ny < 0 || 5 <= nx || 5 <= ny || isVisitied[nx][ny] || dis > 2 || place[nx][ny] == 'X') continue;

                if (place[nx][ny] == 'P') return false;

                isVisitied[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] p = {{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}};
        int[] answer = solution(p);
        for (int a : answer) {
            System.out.println(a);
        }

    }
}
