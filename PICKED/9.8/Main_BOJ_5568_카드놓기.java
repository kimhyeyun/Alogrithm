import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_BOJ_5568_카드놓기 {

    static String[] number;
    static boolean[] isvisited;
    static int n, k;
    static Map<String, Boolean> numMap;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numMap = new HashMap<>();
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        number = new String[n];
        isvisited = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            number[i] = br.readLine();
        }

        DFS(0, "", 0);

        System.out.println(numMap.size());
    }
    private static void DFS(int idx, String string, int cnt) {
        if(cnt == k){
            if(!numMap.containsKey(string))
                numMap.put(string, true);

            return;
        }

        for(int i = 0; i < n ; i++){
            if(!isvisited[i]){
                isvisited[i] = true;
                DFS(i, string+number[i], cnt+1);
                isvisited[i] = false;
            }
        }
    }

}
