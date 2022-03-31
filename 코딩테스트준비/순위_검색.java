import java.util.*;

public class 순위_검색 {
    static Map<String, List> conditions = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        for (String i : info) {
            makeKey(i);
        }

        List<String> keySet = new ArrayList<>();
        for (Map.Entry<String, List> entry : conditions.entrySet()) {
            if(keySet.contains(entry.getKey())) continue;

            keySet.add(entry.getKey());
            Collections.sort(entry.getValue());
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] tmp = query[i].split(" and | ");
            String key = tmp[0] + tmp[1] + tmp[2] + tmp[3];

            if (!conditions.containsKey(key)) {
                answer[i] = 0;
                continue;
            }

            System.out.println(tmp[4]);
            List<Integer> scores = conditions.get(key);
            if (tmp[4].equals("-")) {
                answer[i] = scores.size();
            } else {
                int score = Integer.parseInt(tmp[4]);
                answer[i] = binarySearch(scores,score);
            }
        }

        return answer;
    }

    private static int binarySearch(List<Integer> scores, int score) {
        int left = 0, right = scores.size(), mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            if (scores.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return scores.size() - left;
    }

    private static void makeKey(String info) {
        String[] choice = info.split(" ");
        String[] language = {choice[0], "-"};
        String[] part = {choice[1], "-"};
        String[] career = {choice[2], "-"};
        String[] food = {choice[3], "-"};
        int score = Integer.parseInt(choice[4]);

        for (int i = 0; i < language.length; i++) {
            for (int j = 0; j < part.length; j++) {
                for (int k = 0; k < career.length; k++) {
                    for (int h = 0; h < food.length; h++) {
                        String key = language[i] + part[j] + career[k] + food[h];
                        List<Integer> scores = new ArrayList<>();
                        if (conditions.containsKey(key)) {
                            scores = conditions.get(key);
                        }
                        scores.add(score);
                        conditions.put(key, scores);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza -", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        int[] ans = solution(info, query);

        for (int a : ans) {
            System.out.println(a);
        }
    }
}
