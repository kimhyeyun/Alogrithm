import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1976_여행가자 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] floyd = new int[n][n];
        int[] cities = new int[m];

        for(int i = 0 ; i < n ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n; j++){
                floyd[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(i == j)
                    floyd[i][j] = 1;
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < m ; i++){
            cities[i] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }

        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(floyd[i][k] == 1 && floyd[k][j] == 1)
                        floyd[i][j] = 1;
                }
            }
        }
        
        boolean flag = true;
        for(int i = 0; i < m-1 ; i++){
            if(floyd[cities[i]][cities[i+1]] == 0){
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "YES" : "NO");

    }
}
