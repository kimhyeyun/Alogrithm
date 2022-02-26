import java.util.*;

public class Programmers_주차_요금_계산 {

    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<String, Integer> timeByCar = new HashMap<>();
        Map<String, Integer> totalTimeByCar = new HashMap<>();

        for (String record : records) {
            String[] recordByCar = record.split(" ");
            int time = toMinutes(recordByCar[0]);
            if (recordByCar[2].equals("IN")) {
                timeByCar.put(recordByCar[1], time);
            } else {
                int startTime = timeByCar.get(recordByCar[1]);
                timeByCar.remove(recordByCar[1]);

                int duringTime = time - startTime;
                totalTimeByCar.put(recordByCar[1], totalTimeByCar.getOrDefault(recordByCar[1], 0) + duringTime);
            }
        }

        if (!timeByCar.isEmpty()) {
            for (String key : timeByCar.keySet()) {
                int endTime = toMinutes("23:59");
                int startTime = timeByCar.get(key);
                int duringTime = endTime - startTime;

                totalTimeByCar.put(key, totalTimeByCar.getOrDefault(key, 0) + duringTime);
            }
        }

        List<String> keys = new ArrayList<>(totalTimeByCar.keySet());
        Collections.sort(keys);
        answer = new int[keys.size()];

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            int time = totalTimeByCar.get(key);

            answer[i] += fees[1];
            time -= fees[0];

            if (time > 0) {
                time = (int) Math.ceil((double) time / fees[2]);
                answer[i] += time * fees[3];
            }
        }


        return answer;
    }

    private static int toMinutes(String str) {
        String[] s = str.split(":");

        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        return h * 60 + m;
    }

    public static void main(String[] args) {
        int[] fees = {120, 0, 60, 591};
        String[] records = {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"};

        int[] answer = solution(fees, records);
        for (int ans : answer) {
            System.out.println(ans);
        }
    }
}
