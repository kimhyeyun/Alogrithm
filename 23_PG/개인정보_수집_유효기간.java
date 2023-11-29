import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 개인정보_수집_유효기간 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        String[] s1 = today.split("\\.");
        int tYear = Integer.parseInt(s1[0]);
        int tMon = Integer.parseInt(s1[1]);
        int tDay = Integer.parseInt(s1[2]);

        Map<String, Integer> term = new HashMap<>();
        for (String t : terms) {
            String[] s = t.split(" ");
            term.put(s[0], Integer.parseInt(s[1]));
        }


        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            String[] str = split[0].split("\\.");

            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);

            year += term.get(split[1]) / 12;
            month += term.get(split[1]) % 12;

            if(month > 12) {
                year += (month / 12);
                month %= 12;
            }

            day -= 1;
            if (day == 0) {
                month -= 1;
                day = 28;
            }
            if (month == 0) {
                year -= 1;
                month = 12;
            }

            if (tYear > year) {
                answer.add(i + 1);
            } else if(tYear == year) {
                if (tMon < month) continue;
                else if (tMon > month) answer.add(i + 1);
                else {
                    if (tDay > day) answer.add(i + 1);
                }
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}), new int[]{1, 3});
        Assertions.assertArrayEquals(solution("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}), new int[]{1, 4, 5});
    }
}
