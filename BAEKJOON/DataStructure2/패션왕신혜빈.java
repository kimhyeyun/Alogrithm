package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class 패션왕신혜빈{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer sTokenizer;

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            Map<String, Integer> clothes = new HashMap<>();
            int n = Integer.parseInt(br.readLine());

            for(int i = 0 ; i < n ; i++){
                sTokenizer = new StringTokenizer(br.readLine());
                String type = sTokenizer.nextToken();
                type = sTokenizer.nextToken();

                clothes.put(type, clothes.getOrDefault(type, 0)+1);
            }

            Iterator<Integer> iterator = clothes.values().iterator();
            int ans = 1;

            while(iterator.hasNext()){
                ans *= iterator.next().intValue()+1;
            }

            sb.append(ans-1).append("\n");
        }

        System.out.print(sb);
        

    }
}