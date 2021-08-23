package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class 나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        Map<Integer, String> pocketMon = new HashMap<>();
        Map<String, Integer> pocketMon2 = new HashMap<>();

        for(int i = 1; i <= N; i++){
            String s = br.readLine();
            pocketMon.put(i, s);
            pocketMon2.put(s, i);
        }

        Set<Entry<Integer, String>> entrySet = pocketMon.entrySet();

        for(int i = 0; i < M; i++){
            String input = br.readLine();

            if(Character.isDigit(input.charAt(0))){
                sb.append(pocketMon.get(Integer.parseInt(input))).append("\n");
            }
            else{
                // 시간초과
                /* for(Entry<Integer, String> entry : entrySet){
                    if(entry.getValue().equals(input))
                        sb.append(entry.getKey()).append("\n");
                } */
                
                sb.append(pocketMon2.get(input)).append("\n");
            }
        }

        System.out.println(sb);

    }
    
}
