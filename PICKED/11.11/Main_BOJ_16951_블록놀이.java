import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16951_블록놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int min = Integer.MAX_VALUE;

        int[] towers = new int[N];
        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            towers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int i = 0 ; i < N ; i++){
            int[] tmp = new int[N];

            tmp[i] = towers[i];

            boolean flag = false;
            for(int j = i-1; j >= 0 ; j--){
                tmp[j] = tmp[j+1] - K;
                if(tmp[j] <= 0){
                    flag = true;
                    break;
                }
            }

            if(flag)    continue;

            for(int j = i+1; j < N ;j++){
                tmp[j] = tmp[j-1] + K;
            }

            int cnt = 0;
            for(int j = 0 ; j < N ; j++){
                if(towers[j] != tmp[j])
                    cnt++;
            }
            min = min < cnt ? min : cnt;
        }

        System.out.println(min);
    }
}
