import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> o2.getValue() - o1.getValue());

        for (Map.Entry<Integer, Integer> entry : entries) {
            if(entry.getValue() >= k) {
                answer += 1;
                break;
            }else{
                k -= entry.getValue();
                answer += 1;
            }
        }

        return answer;
    }
}