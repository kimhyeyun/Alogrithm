import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_10816_숫자카드2{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        Map<Integer, Integer> cards = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int x = Integer.parseInt(stringTokenizer.nextToken());
            cards.put(x, cards.getOrDefault(x, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ;i < M ; i++){
            int x = Integer.parseInt(stringTokenizer.nextToken());
            if(cards.containsKey(x))
                sb.append(cards.get(x));
            else
                sb.append(0);

            sb.append(" ");
        }

        System.out.println(sb);
    }
}