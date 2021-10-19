import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 섬연결하기 {

    static class bridge implements Comparable<bridge>{
        int start;
        int destination;
        int cost;

        bridge(int start, int destination, int cost){
            this.start = start;
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(bridge o) {
            return this.cost - o.cost;
        }

    }

    static ArrayList<bridge> adj[];
    static ArrayList<bridge> mst;
    static boolean[] isVisited;

    public static int solution(int n, int[][] costs) {
        int answer = 0;

        adj = new ArrayList[n];
        mst = new ArrayList<>();
        isVisited = new boolean[n];

        for(int i = 0 ; i < n; i++){
            adj[i] = new ArrayList<>();
        }

        for(int[] c : costs){
            adj[c[0]].add(new bridge(c[0], c[1], c[2]));
            adj[c[1]].add(new bridge(c[1], c[0], c[2]));
        }

        PriorityQueue<bridge> pq = new PriorityQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(0);
        mst.add(new bridge(-1, 0, 0));
        isVisited[0] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            
            for(bridge b : adj[now]){
                if(!isVisited[b.destination])
                    pq.offer(b);
            }

            while(!pq.isEmpty()){
                bridge b = pq.poll();

                if(!isVisited[b.destination]){
                    queue.add(b.destination);
                    isVisited[b.destination] = true;
                    mst.add(b);
                    answer += b.cost;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] costs = {{0,1,1}, {0,2,2}, {1,2,5}, {1,3,1}, {2,3,8}};
        System.out.println(solution(4, costs));
    }
}
