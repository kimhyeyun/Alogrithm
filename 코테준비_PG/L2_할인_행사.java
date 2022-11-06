import java.util.HashMap;
import java.util.Map;

public class L2_할인_행사 {
    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> wantProducts = new HashMap<>();
        Map<String, Integer> discountProducts;

        for (int i = 0; i < want.length; i++) {
            wantProducts.put(want[i], number[i]);
        }

        for (int i = 0; i <= discount.length - 10; i++) {
            discountProducts = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                discountProducts.put(discount[j], discountProducts.getOrDefault(discount[j], 0) + 1);
            }

            boolean isPossible = false;
            for (String key : wantProducts.keySet()) {
                if(!discountProducts.containsKey(key)){
                    isPossible = false;
                    break;
                }
                if(discountProducts.get(key) < wantProducts.get(key)){
                    isPossible = false;
                    break;
                }

                isPossible = true;
            }
            if(isPossible) answer += 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        System.out.println(solution(want, number, discount));

    }
}
