import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 모두0으로만들기 {
    
    static ArrayList<Integer> adj[];
    static boolean[] isVisited;
    static int[] indegree;
    static long[] longA;
    static long answer;
    public static long solution(int[] a, int[][] edges) {
        answer = 0;

        longA = new long[a.length];
        adj = new ArrayList[a.length];
        indegree = new int[a.length];
        isVisited = new boolean[a.length];

        long sum = 0;
        for(int i = 0 ; i < a.length ; i++){
            sum += a[i];
            longA[i] = a[i];
            adj[i] = new ArrayList<>();
        }

        if(sum != 0)
            return -1;

        for(int i = 0 ; i < edges.length ; i++){
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
            
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        
        BFS();

        return answer;
    }

    private static void BFS() {
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0 ; i < indegree.length ; i++){
            if(indegree[i] == 1)
                queue.offer(i);
        }

        while(!queue.isEmpty()){
            int now = queue.poll();
            isVisited[now] = true;

            for(int next : adj[now]){
                if(!isVisited[next]){
                    indegree[next]--;
                    longA[next] += longA[now];
                    answer += Math.abs(longA[now]);
                    longA[now] = 0;

                    if(indegree[next] == 1)
                        queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0,3}};

        System.out.println(solution(a, edges));
    }
}
