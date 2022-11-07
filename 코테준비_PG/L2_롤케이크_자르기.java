import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L2_롤케이크_자르기 {
    public static int solution(int[] topping) {
        int answer = 0;

        Set<Integer> brotherTopping = new HashSet<>();
        Map<Integer, Integer> myTopping = new HashMap<>();

        for (int i = 0; i < topping.length; i++) {
            myTopping.put(topping[i], myTopping.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 0; i < topping.length; i++) {
            brotherTopping.add(topping[i]);
            if(myTopping.get(topping[i]) == 1) myTopping.remove(topping[i]);
            else myTopping.put(topping[i], myTopping.get(topping[i]) - 1);

            if(myTopping.size() < brotherTopping.size()) break;
            if(myTopping.size() == brotherTopping.size()) answer += 1;
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] topping = {1, 2, 3, 1, 4};

        System.out.println(solution(topping));
    }
}
