import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main_BOJ_1958_LCS3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = new String[3];
        int[] len = new int[3];
        for(int i = 0 ; i < 3; i++){
            str[i] = br.readLine();
            len[i] = str[i].length();
        }

        int[][][] DP = new int[len[0]+1][len[1]+1][len[2]+1];

        for(int i = 1 ; i <= len[0]; i++){
            for(int j = 1 ; j <= len[1] ; j++){
                for(int k = 1 ; k <= len[2] ; k++){
                    if(str[0].charAt(i-1) == str[1].charAt(j-1) && str[1].charAt(j-1) == str[2].charAt(k-1)){
                        DP[i][j][k] = DP[i-1][j-1][k-1] + 1;
                    }
                    else{
                        DP[i][j][k] = Math.max(DP[i-1][j][k], Math.max(DP[i][j-1][k], DP[i][j][k-1]));
                    }
                }
            }
        }

        System.out.println(DP[len[0]][len[1]][len[2]]);
    }
}
