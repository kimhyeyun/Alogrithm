import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_2660_회장뽑기 {
    static int N;
    static int[][] friendly;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        friendly = new int[N+1][N+1];

        while(true){
            stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            if(a == -1 && b == -1)
                break;

            friendly[a][b] = 1;
            friendly[b][a] = 1;
        }

        for(int k = 1; k < N+1 ; k++){
            for(int i = 1; i < N+1 ; i++){
                for(int j = 1; j < N+1; j++){
                    if(i == j) continue;
                    if(friendly[i][k] != 0 && friendly[k][j] != 0){
                        if(friendly[i][j] == 0)
                            friendly[i][j] = friendly[i][k] + friendly[k][j];
                        else if(friendly[i][j] > friendly[i][k] + friendly[k][j])
                            friendly[i][j] = friendly[i][k] + friendly[k][j];
                    }
                }
            }
        }

/*         for(int[] fr : friendly){
            for(int f : fr)
                System.out.print(f + " ");

            System.out.println();
        }
 */
        
        int min = Integer.MAX_VALUE;
        List<Integer> candidate = new ArrayList<>();
        int[] ans = new int[N+1];
        for(int i = 1; i < N+1 ;i++){
            int cnt = 0;
            for(int j = 1; j < N+1; j++){
                cnt = cnt < friendly[i][j] ? friendly[i][j] : cnt;
            }
            ans[i] = cnt;
            min = min > cnt ? cnt : min;
        }

        for(int i = 1; i <= N ;i++){
            if(min == ans[i]){  
                candidate.add(i);
            }
        }

        Collections.sort(candidate);
        System.out.println(min + " " + candidate.size());
        for(int c : candidate)
            System.out.print(c + " ");
        System.out.println();
    }
}
