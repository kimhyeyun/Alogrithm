import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1507_궁금한민호 {
    static int[][] minGraph;
    static int[][] cpyGraph;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int n = Integer.parseInt(br.readLine());
        minGraph = new int[n+1][n+1];
        cpyGraph = new int[n+1][n+1];

        for(int i = 1 ; i <= n ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                int d = Integer.parseInt(stringTokenizer.nextToken());
                minGraph[i][j] = d;
                cpyGraph[i][j] = d;
            }
        }

        // floyd - Warshall algorithm 이용
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n ; i++){
                for(int j = 1; j <= n ; j++){
                    // 자기자신은 패스
                    if(k == i || k == j)
                        continue;
                    
                    // 최단 거리인데 그보다 작은 게 나오면 옳지 않음으로 -1 출력 후 종료
                    if(minGraph[i][j] > minGraph[i][k] + minGraph[k][j]){
                        System.out.println(-1);
                        return;
                    }

                    // 같으면 직행은 없고 경유지가 존재
                    if(minGraph[i][j] == minGraph[i][k] + minGraph[k][j]){
                        cpyGraph[i][j] = 0;
                    }
                }
            }
        }

        int answer = 0;
        for(int i = 1 ; i <= n ; i++){
            for(int j = i; j <=n ; j++){
                answer += cpyGraph[i][j];
            }
        }
        System.out.println(answer);
    }
}
