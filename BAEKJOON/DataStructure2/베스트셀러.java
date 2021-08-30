package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class 베스트셀러 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> books = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){
            String book = br.readLine();
            books.put(book, books.getOrDefault(book, 0)+1);
        }

        List<Map.Entry<String, Integer>> entries = new LinkedList<>(books.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                int result = o2.getValue() - o1.getValue();

                if(result == 0){
                    result = o1.getKey().compareTo(o2.getKey());
                }

                return result;
            }
            
        });

        System.out.println(entries.get(0).getKey());

    }
}
