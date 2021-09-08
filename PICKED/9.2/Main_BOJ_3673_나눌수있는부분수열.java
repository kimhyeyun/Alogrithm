import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3673_나눌수있는부분수열 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());
        long[] sum, prefix;

        while(testCase-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());

            sum = new long[n+1];
            prefix = new long[d];

            stringTokenizer = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n ; i++){
                // long x = Long.parseLong(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                sum[i] = (sum[i-1] + x) % d;
                prefix[(int) sum[i]]++;
            }

            long result = prefix[0];
            for(int i = 0 ; i < d; i++){
                if(prefix[i] >= 2){
                    result += (prefix[i] * (prefix[i]-1))/2;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
    
}
