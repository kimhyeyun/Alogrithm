import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_22865_가장_먼_곳 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer stringTokenizer;
    static int N, M;
    static int[] friends;
    static ArrayList<road> adj[];
    static int[][] dist;
    static int maxDist = -1, maxNum = -1;

    public static void main(String[] args) throws IOException {
        init();

        for(int i = 0 ; i < 3; i++){
            dijkstra(i);
        }

        for(int i = 1 ; i < N+1; i++){
            if(i == friends[0] || i == friends[1] || i == friends[2])   continue;

            int min = Math.min(dist[i][0], Math.min(dist[i][1], dist[i][2]));
            if(min == maxDist && maxNum > i)    maxNum = i;
            else if(min > maxDist){
                maxDist = min;
                maxNum = i;
            }

        }

        bw.write(Integer.toString(maxNum));
        bw.flush();

    }

    private static void dijkstra(int start) {
        PriorityQueue<road> pq = new PriorityQueue<>();
        pq.offer(new road(friends[start], 0));

        dist[friends[start]][start] = 0;

        while(!pq.isEmpty()){
            road cur = pq.poll();
            int curDist = cur.distance;

            if(dist[cur.num][start] < curDist)  continue;;

            for(road next : adj[cur.num]){
                if(dist[next.num][start] > curDist + next.distance){
                    dist[next.num][start] = curDist + next.distance;
                    pq.offer(new road(next.num, curDist+ next.distance));
                }
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        friends = new int[3];

        dist = new int[N+1][3];
        for(int i = 0 ; i < N+1 ; i++){
            Arrays.fill(dist[i], 987654321);
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        friends[0] = Integer.parseInt(stringTokenizer.nextToken());
        friends[1] = Integer.parseInt(stringTokenizer.nextToken());
        friends[2] = Integer.parseInt(stringTokenizer.nextToken());

        M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N+1];
        for(int i = 1 ; i <= N ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int distance = Integer.parseInt(stringTokenizer.nextToken());

            adj[x].add(new road(y, distance));
            adj[y].add(new road(x, distance));
        }
    }

    private static class road implements Comparable<road> {
        int num;
        int distance;

        public road(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(road o) {
            return this.distance - o.distance;
        }
    }
}
