import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_2776_암기왕{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){
            int N = Integer.parseInt(br.readLine());
            Set<Integer> set = new HashSet<>();

            stringTokenizer = new StringTokenizer(br.readLine());
            for(int i = 0; i < N ; i++){
                set.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < M ; i++){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                
                if(set.contains(x))
                    sb.append(1).append("\n");
                else    
                    sb.append(0).append("\n");
            }
        }

        System.out.print(sb);
    }
}