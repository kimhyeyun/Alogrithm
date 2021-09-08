import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1633_최고의팀만들기 {
    
    static int[] white = new int[1001];
    static int[] black = new int[1001];
    static int[][][] dp = new int[1001][16][16];
    static int idx = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while((str = br.readLine()) != null){
            String[] s = str.split(" ");
            
            white[idx] = Integer.parseInt(s[0]);
            black[idx++] = Integer.parseInt(s[1]);
        }

        System.out.println(DFS(0, 0, 0));
    }
    private static int DFS(int i, int wIdx, int bIdx) {

        if(wIdx == 15 && bIdx == 15)
            return 0;
        if(i == idx)
            return 0;
        
        if(dp[i][wIdx][bIdx] != 0)
            return dp[i][wIdx][bIdx];

        // 1. i번째 선수 선택안함
        int ans = DFS(i+1, wIdx, bIdx);

        // 2. white로 선택
        if(wIdx < 15)
            ans = Math.max(ans, DFS(i+1, wIdx+1, bIdx) + white[i]);

        // 3. black으로 선택
        if(bIdx < 15)
            ans = Math.max(ans, DFS(i+1, wIdx, bIdx+1) + black[i]);
        
        dp[i][wIdx][bIdx] = ans;

        return dp[i][wIdx][bIdx];
    }
}
