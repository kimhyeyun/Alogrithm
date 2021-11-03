import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2563_색종이 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        boolean[][] paper = new boolean[100][100];

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            for(int i = x; i < x+10;i++){
                for(int j = y; j < y+10 ;j++){
                    paper[i][j] = true;
                }
            }
        }

        int ans = 0;
        for(int i = 0 ; i < 100 ; i++){
            for(int j = 0 ; j < 100 ; j++){
                if(paper[i][j])
                    ans++;

            }
        }

        System.out.println(ans);
    }
}
