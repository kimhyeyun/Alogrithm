import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class 가장먼노드 {

    static List<Integer> graph[];
    static int[] dist;
    static int max = Integer.MAX_VALUE;

    public static int solution(int n, int[][] edge) {
        int answer = 1;

        graph = new ArrayList[n+1];
        for(int i = 0 ; i < n+1; i++){
            graph[i] = new ArrayList<>();
        }
        dist = new int[n+1];
    
        for(int i = 0 ; i < edge.length ; i++){

            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }

        dijkstra();
        
        Integer[] d = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        Arrays.sort(d, Collections.reverseOrder());

        int max = d[1];
        for(int i = 2 ; i < d.length ; i++){
            if(max == d[i])
                answer++;
            else
                break;
        }

        return answer;
    }
    private static void dijkstra() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        pq.offer(1);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while(!pq.isEmpty()){
            int nowNode = pq.poll();

            for(int next : graph[nowNode]){
                if(dist[next] > dist[nowNode] + 1){
                    dist[next] = dist[nowNode] + 1;
                    pq.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(6, edge));
    }
}
