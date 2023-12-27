import java.util.*;
class Solution {
      public static int solution(int n, int[] lost, int[] reserve) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, 1);
        }

        for (int l : lost) {
            map.put(l, map.get(l) - 1);
        }
        for (int r : reserve) {
            map.put(r, map.get(r) + 1);
        }

        for (int key : map.keySet()) {
            if (map.get(key) == 0) {
                if (key - 1 > 0 && map.get(key - 1) == 2) {
                    map.put(key - 1, map.get(key - 1) - 1);
                    map.put(key, map.get(key) + 1);
                }else if(key + 1 <= n && map.get(key + 1) == 2){
                    map.put(key + 1, map.get(key + 1) - 1);
                    map.put(key, map.get(key) + 1);
                }
            }
        }

        int answer = 0;
        for (int key : map.keySet()) {
            if(map.get(key) > 0) answer += 1;
        }

        return answer;
    }
}