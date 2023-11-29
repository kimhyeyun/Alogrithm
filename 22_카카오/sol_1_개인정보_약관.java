import java.util.*;

public class sol_1_개인정보_약관 {
    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> periods = new HashMap<>();

        String[] t = today.split("\\.");

        int tYear = Integer.parseInt(t[0]);
        int tMonth = Integer.parseInt(t[1]);
        int tDay = Integer.parseInt(t[2]);
        System.out.println(tYear + " " + tMonth + " " + tDay);

        for(String term : terms){
            String[] s = term.split(" ");
            periods.put(s[0], Integer.parseInt(s[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");
            String[] days = s[0].split("\\.");

            int year = Integer.parseInt(days[0]);
            int month = Integer.parseInt(days[1]);
            int day = Integer.parseInt(days[2]);

            month += periods.get(s[1]);

            while (month > 12) {
                month -= 12;
                year += 1;
            }
            if (day == 1) {
                month -= 1;
                day = 28;
            } else {
                day -= 1;
            }

            if(month == 0){
                year -= 1;
                month = 12;
            }

            System.out.println(year + " " + month + " " + day);
            if(tYear < year) continue;
            if (year < tYear) {
                answer.add(i + 1);
                continue;
            }

            if(tMonth < month) continue;
            if (month < tMonth) {
                answer.add(i + 1);
                continue;
            }

            if(tDay < day) continue;
            if (day < tDay) {
                answer.add(i + 1);
                continue;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
//        String[] terms = {"A 6", "B 12", "C 3"};
        String[] terms = {"Z 3", "D 5"};
//        String[] privaries = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        solution("2020.01.01", terms, privacies);
    }
}
