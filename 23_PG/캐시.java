import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 캐시 {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new ArrayList<>();

        for (String city : cities) {
            city = city.toLowerCase();

            if (list.contains(city)) {
                answer += 1;
                list.remove(city);
                list.add(city);
            }else{
                answer += 5;
                list.add(city);
                if (list.size() > cacheSize) {
                    list.remove(0);
                }
            }
        }
        return answer;
    }

    @Test
    void test() {
        assertEquals(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}), 50);
        assertEquals(solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}), 60);
    }
}
