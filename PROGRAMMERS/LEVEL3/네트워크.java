import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 네트워크 {

    static boolean[] isVisited;
    static List<Integer>[] adj;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        isVisited = new boolean[n];
        adj = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i == j)
                    continue;
                if(computers[i][j] == 1){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        
        for(int i = 0 ; i < n ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                BFS(i);
                answer++;
            }
        }

        return answer;
    }

    private static void BFS(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);

        while(!queue.isEmpty()){
            int now = queue.poll();
            isVisited[now] = true;

            for(int next : adj[now]){
                if(!isVisited[next]){
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};

        System.out.println(solution(3, computers));
    }
}
