package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.*;

public class Main_BOJ_11652_카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Long, Integer> cards = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            long x = Long.parseLong(br.readLine());
            cards.put(x, cards.getOrDefault(x, 0) + 1);
        }

        List<Map.Entry<Long, Integer>> list = new ArrayList<Map.Entry<Long, Integer>>();
        list.addAll(cards.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            @Override
            public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                if(o1.getValue().intValue() == o2.getValue().intValue())
                    return Long.compare(o1.getKey().longValue(), o2.getKey().longValue());
                return Integer.compare(o2.getValue().intValue(), o1.getValue().intValue());
            }
        });

        System.out.println(list.get(0).getKey());
    }
}
