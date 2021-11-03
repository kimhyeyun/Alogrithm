import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16236_아기상어 {

    static class pair{
        int x;
        int y;

        pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, sharkX, sharkY, minX, minY, minDist, sharkSize = 2, eating = 0, time = 0;
    static int[][] map, dist;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(map[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true){
            dist = new int[N][N];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            BFS(sharkX, sharkY);

            if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE){
                eating++;
                map[minX][minY] = 0;
                sharkX = minX;
                sharkY = minY;
                time += dist[minX][minY];

                if(eating == sharkSize){
                    sharkSize++;
                    eating = 0;
                }
            }
            else
                break;
        }
        System.out.println(time);
    }
    private static void BFS(int x, int y) {
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(x, y));

        while(!q.isEmpty()){
            pair now = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || map[nx][ny] > sharkSize || dist[nx][ny] != 0)  continue;

                dist[nx][ny] = dist[now.x][now.y] + 1;

                if(map[nx][ny] != 0 && map[nx][ny] < sharkSize){
                    if(minDist > dist[nx][ny]){
                        minDist = dist[nx][ny];
                        minX = nx;
                        minY = ny;
                    }
                    else if(minDist == dist[nx][ny]){
                        if(minX == nx){
                            if(minY > ny){
                                minX = nx;
                                minY = ny;
                            }
                        }
                        else if(minX > nx){
                            minX = nx;
                            minY = ny;
                        }
                    }
                }
                q.offer(new pair(nx, ny));
            
            }
        }
    }
}
