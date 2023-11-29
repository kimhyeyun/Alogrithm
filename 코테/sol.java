import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sol {

    static int answer = 0;

    public void s(String  s) {
        System.out.println(s.substring(5));

    }

    public int solution(int[] weather, int capacity){
        DFS(0, 0, false, weather, capacity);
        System.out.println(answer);

        return answer;
    }

    public static void DFS(int index, int savedElec, boolean prev, int[] weather, int capacity) {
        if (index == weather.length) {
            answer = Math.max(answer, index);
            return;
        }

        if (savedElec == 0 && weather[index] == 1) {
            answer = Math.max(answer, index);
            return;
        }

        if (weather[index] == 1) {
            DFS(index + 1, savedElec - 1, false, weather, capacity);
        } else {
            if (savedElec > 0) {
                DFS(index + 1, savedElec - 1, false, weather, capacity);
            }

            if (!prev) {
                DFS(index + 1, 1, true, weather, capacity);
            } else {
                int e = Math.min(savedElec + 1, capacity);
                DFS(index + 1, e, true, weather, capacity);
            }
        }

    }

    @Test
    void test() {
//        assertEquals(solution(new int[]{0,0,0,1,1,1}, 2), 5);
//        assertEquals(solution(new int[]{0,0,1,1,1,1}, 50), 4);
//        assertEquals(solution(new int[]{0,1,0,0,0,1,1}, 100), 7);
//        assertEquals(solution(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1}, 10), 15);

        s("(010)53115980");
    }
}
