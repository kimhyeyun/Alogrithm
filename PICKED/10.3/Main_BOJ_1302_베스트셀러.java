import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main_BOJ_1302_베스트셀러 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> books = new HashMap<>();

        while(N-- > 0){
            String title = br.readLine();
            books.put(title, books.getOrDefault(title, 0)+1);
        }

        ArrayList<String> titleSet = new ArrayList<>(books.keySet());
        Collections.sort(titleSet, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                int result = books.get(o2)-books.get(o1);

                if(result == 0)
                    result = o1.compareTo(o2);

                return result;
            }
        });

        System.out.println(titleSet.get(0));
    }
}
