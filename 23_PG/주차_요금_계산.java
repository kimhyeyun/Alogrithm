import java.util.*;

public class 주차_요금_계산 {
    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> totalOfTimeByCar = new HashMap<>();

        for (String record : records) {
            String[] s = record.split(" ");
            String[] s2 = s[0].split(":");

            int time = Integer.parseInt(s2[0]) * 60 + Integer.parseInt(s2[1]);

            if(s[2].equals("IN")) map.put(s[1], time);
            else {
                int entry = map.get(s[1]);
                map.remove(s[1]);

                int duration = time - entry;
                totalOfTimeByCar.put(s[1], totalOfTimeByCar.getOrDefault(s[1], 0) + duration);
            }
        }

        int endTime = 23 * 60 + 59;
        if (!map.isEmpty()) {
            for (String car : map.keySet()) {
                int start = map.get(car);
                int duration = endTime - start;

                totalOfTimeByCar.put(car, totalOfTimeByCar.getOrDefault(car, 0) + duration);
            }
        }

        List<String> cars = new ArrayList<>(totalOfTimeByCar.keySet());
        Collections.sort(cars);

        int[] answer = new int[cars.size()];

        for (int i = 0; i < cars.size(); i++) {
            String car = cars.get(i);
            int time = totalOfTimeByCar.get(car);

            answer[i] += fees[1];

            time -= fees[0];

            if (time <= 0) continue;

            time = (int) Math.ceil((double) time / fees[2]);
            answer[i] += time * fees[3];
        }

        return answer;
    }
}
