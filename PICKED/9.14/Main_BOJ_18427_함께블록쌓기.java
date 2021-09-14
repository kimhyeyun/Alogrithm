import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_18427_함께블록쌓기 {
    static final int mod = 10007;
    static int N, M, H;
    static int[][] DP;
    static List<Integer>[] studentsHas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());

        DP = new int[N+1][H+1];
        DP[0][0] = 1;
        studentsHas = new ArrayList[N+1];
        for(int i = 0 ; i < N+1 ; i++){
            studentsHas[i] = new ArrayList<>();
        }

        for(int i = 1 ; i <= N ;i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            
            while(stringTokenizer.hasMoreTokens()){
                int block = Integer.parseInt(stringTokenizer.nextToken());
                studentsHas[i].add(block);
            }
        }

        for(int i = 1; i <= N ; i++){
            DP[i][0] = 1;
            for(int j = 1 ; j <= H ;j++){
                for(int k = 0 ; k < studentsHas[i].size() ; k++){
                    if(j - studentsHas[i].get(k) < 0)
                        continue;
                    
                    // i번째 학생이 하나의 블록 (k번째 블록)을 선택하는 경우
                    DP[i][j] += DP[i-1][j-studentsHas[i].get(k)];
                }
                // i번째 학생이 블록을 사용하지 않는 경우
                DP[i][j] += DP[i-1][j];
                DP[i][j] %= mod;

            }
        }

        System.out.println(DP[N][H]);
    }
}
