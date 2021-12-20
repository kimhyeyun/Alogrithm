import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17836_공주님을_구해라 {
    static int N, M, T;
    static String[][] castle;
    static boolean[][][] isVisited; // 0 : 그람 미소유, 1 : 그람 소유
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    public static void main(String[] args) throws IOException {
        init();
        boolean result = BFS();

        if(!result)
            System.out.println("Fail");
    }

    private static boolean BFS() {
        Queue<node> queue = new LinkedList<>();
        queue.offer(new node(0,0, 0, false));
        isVisited[0][0][0] = false;

        // 0 : 빈 공간, 1 : 벽, 2 : 그람
        while(!queue.isEmpty()){
            node cur = queue.poll();

            if(cur.time > T)  return false;

            if(cur.x == N-1 && cur.y == M-1){
                System.out.println(cur.time);
                return true;
            }

            for(int i = 0 ; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M){
                    if(!cur.gramr){
                        // 그람 없음
                        if(!isVisited[nx][ny][0] && castle[nx][ny].equals("0")){
                            queue.offer(new node(nx, ny, cur.time+1, false));
                            isVisited[nx][ny][0] = true;
                        }
                        else if(!isVisited[nx][ny][0] && castle[nx][ny].equals("2")){
                            queue.offer(new node(nx, ny, cur.time+1, true));
                            isVisited[nx][ny][0] = true;
                        }
                    }
                    else{
                        // 그람 있음
                        if(!isVisited[nx][ny][1]){
                            queue.offer(new node(nx, ny, cur.time+1, true));
                            isVisited[nx][ny][1] = true;
                        }
                    }
                }
            }
        }

        return false;

    }

    private static void init() throws IOException {
        stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        castle = new String[N][M];
        isVisited = new boolean[N][M][2];

        for(int i = 0 ; i < N ; i++){
            castle[i] = br.readLine().split(" ");
        }
    }

    private static class node {
        int x;
        int y;
        int time;
        boolean gramr;

        public node(int x, int y, int time, boolean gramr) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.gramr = gramr;
        }
    }
}
