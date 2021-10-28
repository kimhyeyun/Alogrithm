import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BOJ_18405_경쟁적전염 {

    static class pair{
        int x;
        int y;

        pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class pairWithTime{
        int x;
        int y;
        int time;

        pairWithTime(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int N, K, S, X, Y;
    static int[][] testTube;
    static List<pair> pairList[];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        testTube = new int[N][N];
        pairList = new ArrayList[K+1];
        for(int i = 0 ; i < K+1 ; i++)
            pairList[i] = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                testTube[i][j] = x;

                if(x != 0)
                    pairList[x].add(new pair(i, j));
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        S = Integer.parseInt(stringTokenizer.nextToken());
        X = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        Y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        BFS();

        System.out.println(testTube[X][Y]);
    }

    private static void BFS() {
        Queue<pairWithTime> q = new LinkedList<>();
        for(int i = 1 ; i <= K ;i++){
            for(pair p : pairList[i]){
                q.offer(new pairWithTime(p.x, p.y, 0));
            }
        }

        while(!q.isEmpty()){
            pairWithTime now = q.poll();

            if(now.time == S)   return;

            for(int i = 0 ; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || N <= nx || ny < 0 || N <= ny || testTube[nx][ny] != 0) continue;

                testTube[nx][ny] = testTube[now.x][now.y];
                q.offer(new pairWithTime(nx, ny, now.time+1));
            }
        }
    }
}
