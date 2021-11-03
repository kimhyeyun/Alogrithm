import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2960_에라토스테네스의체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int[] erasto = new int[N+1];
        int cnt = 0;

        for(int i = 2; i < N+1; i++)
            erasto[i] = i;

        for(int i = 2 ; i < N+1 ; i++){
            if(erasto[i] == 0)  continue;

            for(int j = i; j < N+1; j += i){
                if(erasto[j] == 0)  continue;
                else{
                    cnt++;
                    erasto[j] = 0;

                    if(cnt == K){
                        System.out.println(j);
                        return;
                    }
                }
            }
        }
    }
    
}
