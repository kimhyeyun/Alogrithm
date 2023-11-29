import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 거리두기_확인하기 {

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            String[] place = places[i];

            boolean isPossible = true;
            for (int x = 0; x < 5 && isPossible; x++) {
                for (int y = 0; y < 5 && isPossible; y++) {
                    if (place[x].charAt(y) == 'P') {
                        if(!BFS(x,y,place)) isPossible = false;
                    }
                }
            }
            answer[i] = isPossible ? 1 : 0;
        }
        return answer;
    }

    private boolean BFS(int x, int y, String[] place) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || 5 <= nx || 5 <= ny || (nx == x && ny == y)) continue;

                int dist = Math.abs(nx - x) + Math.abs(ny - y);

                if(place[nx].charAt(ny) == 'P' && dist <= 2) return false;
                else if(place[nx].charAt(ny) == 'O' && dist < 2) q.add(new int[]{nx, ny});
            }
        }
        return true;
    }
}
