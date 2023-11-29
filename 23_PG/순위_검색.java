import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 순위_검색 {

    public int[] solution(String[] info, String[] query) {

        Map<String, List<Integer>> infos = init(info);
        List<String> keySet = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : infos.entrySet()) {
            if (keySet.contains(entry.getKey())) {
                continue;
            } else {
                keySet.add(entry.getKey());
                Collections.sort(entry.getValue());
            }
        }

        int[] answer = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            String q = query[i];

            String[] split = q.split(" and | ");
            String key = split[0] + split[1] + split[2] + split[3];
            int score = Integer.parseInt(split[4]);

            if(!infos.containsKey(key)) answer[i] = 0;
            else {
                List<Integer> scores = infos.get(key);
                answer[i] = findCount(scores, score);
            }
        }
        return answer;
    }

    private int findCount(List<Integer> scores, int score) {
        int left = 0, right = scores.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if(scores.get(mid) <score) left = mid + 1;
            else right = mid;
        }

        return scores.size() - left;
    }

    private Map<String, List<Integer>> init(String[] infos) {
        Map<String, List<Integer>> tmp = new HashMap<>();

        for (String info : infos) {
            String[] str = info.split(" ");

            String[] languages = {"-", str[0]};
            String[] jobs = {"-", str[1]};
            String[] careers = {"-", str[2]};
            String[] foods = {"-", str[3]};
            int score = Integer.parseInt(str[4]);

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        for (int h = 0; h < 2; h++) {
                            String s = languages[i] + jobs[j] + careers[k] + foods[h];

                            List<Integer> value = new ArrayList<>();
                            if (tmp.containsKey(s)) value = tmp.get(s);
                            value.add(score);

                            tmp.put(s, value);
                        }
                    }
                }
            }
        }
        return tmp;
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}, new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}), new int[]{1, 1, 1, 1, 2, 4});
    }
}
