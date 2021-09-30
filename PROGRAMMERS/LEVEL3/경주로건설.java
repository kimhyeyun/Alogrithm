import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설 {
    static int[] dx = {-1,0,0,1};   // 상 좌 우 하
    static int[] dy = {0,-1,1,0};
    static boolean[][] isVisited;
    static int[][] costs;
    static int answer;
    static int n;

    public static class road{
        int x;
        int y;
        int dir;
        int cost;

        road(int x, int y, int dir, int cost){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

    }

    public static int solution(int[][] board) {
        answer = Integer.MAX_VALUE;

        costs = board;
        n = board.length;
        isVisited = new boolean[n][n];

        // 처음 시작때 밑으로 & 오른쪽으로 두가지 경우
        BFS(0, 0, 2, 0);
        BFS(0, 0, 3, 0);

        return answer;
    }

    private static void BFS(int x, int y, int dir, int cost) {
        Queue<road> q = new LinkedList<>();
        q.add(new road(x,y,dir,cost));
        isVisited[x][y] = true;

        while(!q.isEmpty()){
            road r = q.poll();

            int nowX = r.x;
            int nowY = r.y;
            int nowD = r.dir;
            int nowC = r.cost;

            if(nowX == n-1 && nowY == n-1){
                answer = Math.min(answer, nowC);
            }

            for(int i = 0 ; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                int nextD = i;
                int nextC = nowC;

                if(nextX < 0 || n <= nextX || nextY < 0 || n <= nextY || costs[nextX][nextY] == 1)
                    continue;

                if(nextD == nowD){
                    nextC += 100;
                }
                else 
                    nextC += 600;

                if(!isVisited[nextX][nextY] || costs[nextX][nextY] >= nextC){
                    isVisited[nextX][nextY] = true;
                    costs[nextX][nextY] = nextC;
                    q.offer(new road(nextX, nextY, nextD, nextC));
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] board = {
            {0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 0}, 
            {0, 0, 1, 0, 0}, 
            {1, 0, 0, 0, 1}, 
            {0, 1, 1, 0, 0}
        };
        System.out.println(solution(board));
    }
}
