import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14891_톱니바퀴 {

    static int[] dir;
    static int[][] gear;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        gear = new int[4][8];

        for(int i = 0 ; i < 4; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < 8; j++){
                gear[i][j] = tmp[j] == '1' ? 1 : 0;
            }
        }

        int K = Integer.parseInt(br.readLine());
        while(K-- > 0){
            dir = new int[4];
            
            stringTokenizer = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int rotateDir = Integer.parseInt(stringTokenizer.nextToken());

            dir[gearNum] = rotateDir;

            for(int i = gearNum ; i < 3; i++){
                if(gear[i][2] != gear[i+1][6])
                    dir[i+1] = dir[i] * -1;
            }

            for(int i = gearNum ; i > 0 ; i--){
                if(gear[i][6] != gear[i-1][2])
                    dir[i-1] = dir[i] * -1;
            }

            Rotate();
            
        }

        int ans = 0;
        for(int i = 0 ; i < 4 ;i++){
            if(gear[i][0] == 1)
                ans += Math.pow(2, i);
        }

        System.out.println(ans);

    }

    private static void Rotate() {
        for(int i = 0 ; i < 4 ; i++){
            if(dir[i] == 1){
                int tmp = gear[i][7];
                for(int j = 7 ; j > 0 ; j--)
                    gear[i][j] = gear[i][j-1];

                gear[i][0] = tmp;
            }
            else if(dir[i] == -1){
                int tmp = gear[i][0];
                for(int j = 1; j < 8 ; j++)
                    gear[i][j-1] = gear[i][j];

                gear[i][7] = tmp;
            }
        }
    }
}
