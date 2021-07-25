import java.util.LinkedList;
import java.util.Queue;

public class 카카오프렌즈컬러링북 {
    static boolean visited[][];

    public static void main(String[] args) {

        int[][] p = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        int[] ans = solution(6, 4, p);

        for(int a : ans){
            System.out.println(a);
        }
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];

        int[] answer = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    int cnt = bfs(i,j,picture,m,n);
                    
                    maxSizeOfOneArea = maxSizeOfOneArea < cnt ? cnt : maxSizeOfOneArea;

                    if(picture[i][j] != 0)
                        numberOfArea++;
                }
            }
        }

        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    private static int bfs(int x, int y, int[][] picture, int m, int n) {

        int cnt = 1;
        boolean zero = false;

        Queue<Pair> q = new LinkedList<>();
        int dx[] = { -1, 0, 0, 1 };
        int dy[] = { 0, -1, 1, 0 };

        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            x = q.peek().x;
            y = q.poll().y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (picture[x][y] == picture[nx][ny] && !visited[nx][ny]) {
                        cnt++;
                        q.add(new Pair(nx,ny));
                        visited[nx][ny] = true;
                        if(picture[x][y] == 0)
                            zero = true;
                    }
                }
            }
        }
        if(zero)
            return 0;

        return cnt;

    }

    public static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
