package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 시간 초과
* ㄸㄸㄸㄸ
* 어떻게 풀어야할란징징징
* Dijkstra는 시간초과인듯;;
* */

public class Main_BOJ_2939_택배_배달 {
    static int R,C,D;
    static int[] start;
    static int[][] time;
    static int[][] dist;
    static int[] dx_RL = {0, 0};
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy_RL = {-1, 1};
    static int[] dy = {0, -1, 1, 0};
    static List<int[]> locations;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;

    public static void main(String[] args) throws IOException {
        init();

        int answer = 0;
        for(int i = 0 ; i < D ; i++){
            Dijkstra(start, locations.get(i));
/*            System.out.println(locations.get(i)[0] + "," + locations.get(i)[1] + " : " +  dist[locations.get(i)[0]][locations.get(i)[1]]);
            System.out.println("-----------------------");
            for(int[] ti : dist){
                for(int t : ti){
                    System.out.print(t+" ");
                }
                System.out.println();
            }
            System.out.println("-----------------------");*/
            answer += dist[locations.get(i)[0]][locations.get(i)[1]];
            start = new int[]{locations.get(i)[0], locations.get(i)[1],time[locations.get(i)[0]][locations.get(i)[1]]};
        }

        for(int i = 0 ; i < D-1; i++){
            answer -= time[locations.get(i)[0]][locations.get(i)[1]];
        }

        System.out.println(answer);
    }

    private static void Dijkstra(int[] startLocation, int[] destLocation) {
        // 왼쪽 오른쪽 (-1, 0) (1, 0)
        // [][0]과 [][C-1]에서만 위아래로 가능
        for(int i = 0 ; i < R ; i++){
            Arrays.fill(dist[i], 987654321);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(startLocation);
        dist[startLocation[0]][startLocation[1]] = time[startLocation[0]][startLocation[1]];
        int curDist = startLocation[2];

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(dist[cur[0]][cur[1]] < curDist)  continue;

            if(cur[1] == 0 || cur[1] == C-1){
                for(int i = 0 ; i < 4; i++){
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if(0 <= nx && nx < R && 0 <= ny && ny < C){
                        if(dist[nx][ny] > dist[cur[0]][cur[1]] + time[nx][ny]){
                            dist[nx][ny] = dist[cur[0]][cur[1]] + time[nx][ny];
                            queue.add(new int[]{nx,ny,curDist + time[nx][ny]});
                        }
                    }
                }
            }
            else{
                for(int i = 0; i < 2; i++){
                    int nx = cur[0] + dx_RL[i];
                    int ny = cur[1] + dy_RL[i];

                    if(0 <= nx && nx < R && 0 <= ny && ny < C){
                        if(dist[nx][ny] > dist[cur[0]][cur[1]] + time[nx][ny]){
                            dist[nx][ny] = dist[cur[0]][cur[1]] + time[nx][ny];
                            queue.add(new int[]{nx,ny,curDist+time[nx][ny]});
                        }
                    }
                }
            }


        }

    }

    private static void init() throws IOException {
        locations = new ArrayList<>();

        stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        time = new int[R][C];
        dist = new int[R][C];

        for(int i = 0 ; i < R ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < C ; j++){
                time[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        start = new int[]{0, 0, time[0][0]};

        D = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < D ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            locations.add(new int[]{x-1, y-1});
        }

    }
}
