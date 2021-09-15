import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_14467_소가길을건너간이유1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        Map<Integer, Integer> cows = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int cowNum = Integer.parseInt(stringTokenizer.nextToken());
            int location = Integer.parseInt(stringTokenizer.nextToken());

            if(cows.containsKey(cowNum)){
                if(cows.get(cowNum) != location)
                    answer++;

            }
           cows.put(cowNum, location);
        }

        System.out.println(answer);
    }
}
