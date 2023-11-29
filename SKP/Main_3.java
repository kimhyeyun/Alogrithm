import java.util.*;

public class Main_3 {
    public static int solution(String[] color, int[] prices) {
        int answer = 0;

        Map<Character, Integer> tops = new HashMap<>();
        Map<Character, Integer> bottoms = new HashMap<>();

        for (String c : color) {
            char top = c.charAt(0);
            char bottom = c.charAt(1);

            tops.put(top, tops.getOrDefault(top, 0) + 1);
            bottoms.put(bottom, bottoms.getOrDefault(bottom, 0) + 1);
        }

        int cnt = 0;
        for (char top : tops.keySet()) {
            int numOfTop = tops.get(top);
            cnt += numOfTop;
            while (true) {
                if(numOfTop == 0) break;

                if (bottoms.containsKey(top) && bottoms.get(top) > 0) {
                    bottoms.put(top, bottoms.getOrDefault(top, 0) - 1);
                    answer += prices[0];
                } else answer += prices[1];

                numOfTop -= 1;
            }
        }

        for (char key : bottoms.keySet()) {
            if(bottoms.get(key) > 0) cnt += bottoms.get(key);
        }

        answer = answer > prices[0] * cnt ? prices[0] * cnt : answer;
        return answer;
    }

    public static void main(String[] args) {

        String[] color = {"RG", "WR", "BW", "GG"};
        int[] price = {2000,6000};

        System.out.println(solution(color, price));
    }
}
