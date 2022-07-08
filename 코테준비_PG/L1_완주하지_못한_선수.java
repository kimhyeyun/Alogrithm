import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1_완주하지_못한_선수 {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) > 0) {
                answer = key;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};

        System.out.println(solution(participant, completion));

    }
}
