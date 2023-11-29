import java.util.HashMap;
import java.util.Map;

public class ebay_sol_1 {
    public String solution(int day1, String dow1, int day2) {
        String[] dow = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < dow.length; i++) {
            map.put(dow[i], i);
        }

        boolean isPre = day2 < day1 ? true : false;
        int diff = Math.abs(day1 - day2) % 7;

        int idx = map.get(dow1);
        if (isPre) {
            for (int i = 0; i < diff; i++) {
                idx -= 1;
                if (idx == -1) idx = 6;
            }
        } else {
            for (int i = 0; i < diff; i++) {
                idx += 1;
                if(idx == 7) idx = 0;
            }
        }

        return dow[idx];
    }

    public static void main(String[] args) {
        int i = 1;
        int sum = 0;
        for (System.out.println(sum); i <= 3; i++) {
            sum += i;
        }
    }
}
