import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/* Dijkstra */

public class 배달 {

    public static class Edge implements Comparable<Edge>{
        int num;
        int distance;

        Edge(int num, int distance){
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub

            return this.distance - o.distance;
        }
    }

    static ArrayList<Edge>[] adj;
    static int dist[];

    public static int solution(int N, int[][] road, int K){
        int answer = 0;

        adj = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i= 0;i < N+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < road.length; i++){
            adj[road[i][0]].add(new Edge(road[i][1], road[i][2]));
            adj[road[i][1]].add(new Edge(road[i][0], road[i][2]));
        }

       /* Dijkstra */
        dijkstra();

        for(int i = 1; i < dist.length; i++){
            if(dist[i] <= K){
                answer++;
            }
        }

        return answer;
    }


    private static void dijkstra() {

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            /* visited[edge.num] = true;
            dist[edge.num] = Math.min(dist[edge.num], edge.distance);

            for(Edge next : adj[edge.num]){
                if(!visited[next.num]){
                    pq.offer(new Edge(next.num, edge.distance + next.distance));
                }
            } */
            
            for(Edge next : adj[edge.num]){
                if(dist[next.num] > dist[edge.num] + next.distance){
                    dist[next.num] = dist[edge.num] + next.distance;
                    pq.offer(next);
                }
            }

        }
    }



    public static void main(String[] args) {
        int[][] r = {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}};

        System.out.println(solution(6, r, 4));
    }
}
