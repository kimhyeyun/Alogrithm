import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1120_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        String A = stringTokenizer.nextToken();
        String B = stringTokenizer.nextToken();

        int aLen = A.length();
        int bLen = B.length();
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0 ; i < bLen-aLen+1; i++){
            int cnt = 0;

            for(int j = i ; j < i+aLen ; j++){
                if(A.charAt(j-i) != B.charAt(j))
                    cnt++;
            }

            answer = Math.min(answer,cnt);
        }

        System.out.println(answer);

    }
    
}
