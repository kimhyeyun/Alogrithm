import java.util.HashMap;
import java.util.Iterator;

public class 위장 {

    public static int solution(String[][] clothes){

        int answer = 1;

        HashMap<String, Integer> set = new HashMap<>();

        for(int i = 0; i < clothes.length;i++){
            String key = clothes[i][1];

            if(!set.containsKey(key)){
                set.put(key, 1);
            }
            else{
                set.put(key, set.get(key)+1);
            }
        }

        Iterator<Integer> it = set.values().iterator();
        while(it.hasNext()){
            answer *= it.next().intValue() + 1;
        }

        return answer-1;
    }
    public static void main(String[] args) {
        
    }
}
