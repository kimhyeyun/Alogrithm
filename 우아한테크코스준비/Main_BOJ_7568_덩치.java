import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_7568_덩치 {

    static class size implements Comparable<size>{
        int x;
        int y;

        size(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(size o) {
            // TODO Auto-generated method stub
            return o.x - this.x;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        // List<size> people = new ArrayList<>();
        Map<size, Integer> people = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();

        for(int i = 0 ; i < N; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            people.put(new size(x, y), i);
            rank.put(i, 1);
        }

        ArrayList<size> kSet = new ArrayList<>(people.keySet());
        Collections.sort(kSet);

        for(int i = 0 ; i < kSet.size() ; i++){
            size nowPerson = kSet.get(i);
            for(int j = 0 ; j < i ; j++){
                if(nowPerson.x < kSet.get(j).x && nowPerson.y < kSet.get(j).y){
                    rank.put(people.get(nowPerson), rank.getOrDefault(people.get(nowPerson), 0) + 1 );
                }
            }
        }

        for(int i : rank.keySet()){
            System.out.print(rank.get(i) + " ");
        }   
        System.out.println();
    }
}
