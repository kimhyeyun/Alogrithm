import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 합승택시요금 {
    public static int solutionFloyd(int n, int s, int a, int b, int[][] fares) {
        
        int[][] floyd = new int[n+1][n+1];
        for(int i = 1 ; i < n+1 ; i++){
            for(int j = 1; j < n+1; j++){
                floyd[i][j] = 20000001;
            }
            floyd[i][i] = 0;
        }

        for(int[] f : fares){
            floyd[f[0]][f[1]] = f[2];
            floyd[f[1]][f[0]] = f[2];
        }

        for(int k = 1 ; k < n+1 ; k++){
            for(int i = 1; i < n+1; i++){
                for(int j = 1 ; j < n+1 ; j++){
                    if(floyd[i][j] > floyd[i][k] + floyd[k][j]){
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }

        int ans = floyd[s][a] + floyd[s][b];
        for(int k = 1; k < n+1 ; k++){
            ans = Math.min(ans, floyd[s][k] + floyd[k][a] + floyd[k][b]);
        }

        return ans;
    }

    static class edge implements Comparable<edge>{
        int index;
        int cost;

        edge(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(edge o) {
            // TODO Auto-generated method stub
            return this.cost - o.cost;
        }
    }

    static ArrayList<edge>[] graph;
    public static int solutionDijkstra(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList[n+1];
        for(int i = 0 ; i < n+1 ; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] f : fares){
            graph[f[0]].add(new edge(f[1], f[2]));
            graph[f[1]].add(new edge(f[0], f[2]));
        }

        int[] costA = new int[n+1];    // c -> a 별 금액
        int[] costB = new int[n+1];    // c -> b 별 금액
        int[] costS = new int[n+1];     // s -> c 별 금액

        costA = dijkstra(a, costA);
        costB = dijkstra(b, costB);
        costS = dijkstra(s, costS);

        for(int i = 1; i < n+1; i++){
            answer = Math.min(answer, costA[i] + costB[i] + costS[i]);
        }

        return answer;
    }

    private static int[] dijkstra(int index, int[] cost) {
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.offer(new edge(index, 0));
        Arrays.fill(cost, 200000001);
        cost[index] = 0;

        while(!pq.isEmpty()){
            edge now = pq.poll();

            for(edge next : graph[now.index]){
                if(cost[next.index] > cost[now.index] + next.cost){
                    cost[next.index] = cost[now.index] + next.cost;
                    pq.offer(next);
                }
            }
        }

        return cost;
    }
    public static void main(String[] args) {
        int[][] fares = {{5, 7,9}, {4,6,4}, {3,6,1}, {3,2,3}, {2,1,6}};

        System.out.println(solutionFloyd(7, 3, 4, 1, fares));
    }
}
