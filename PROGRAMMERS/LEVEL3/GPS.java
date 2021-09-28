import java.util.ArrayList;
import java.util.List;

public class GPS {

    static int INF = 999999999;

    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        List<Integer>[] adj = new List[n+1];
        int[][] dp = new int[k][n+1];

        for(int i = 0 ; i < n+1; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < edge_list.length ; i++){
            int o = edge_list[i][0];
            int t = edge_list[i][1];
            adj[o].add(t);
            adj[t].add(o);
        }

        for(int i = 0 ; i < k ; i++){
            for(int j = 0 ; j < n+1 ; j++){
                dp[i][j] = INF;
            }
        }

        // 맨 처음 거점은 정해져 이씀
        dp[0][gps_log[0]] = 0;

        for(int i = 1 ; i < k ; i++){
            for(int j = 1 ; j < n+1; j++){
                // 이동 x
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);

                // 인접한 노드로 이동을 하는 경우
                for(int next : adj[j]){
                    dp[i][j] = Math.min(dp[i-1][next], dp[i][j]);
                }

                // gps_log[i]와 현재 거점이 일치하지 않으면 변경되었음
                if(j != gps_log[i])
                    dp[i][j]++;
            }
        }

        if(dp[k-1][gps_log[k-1]] < INF)
            return dp[k-1][gps_log[k-1]];

        return -1;
    }

    public static void main(String[] args) {
        int[][] edge_list = {{1,2} , {1,3}, {2,3}, {2,4}, {3,4}, {3,5}, { 4,6}, {5,6} , {5,7}, {6,7}};
        int[] gps_log = {1,2,3,3,6,7};

        System.out.println(solution(7, 10, edge_list, 6, gps_log));
    }

}
