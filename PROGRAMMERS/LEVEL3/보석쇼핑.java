import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 보석쇼핑 {

    public static int[] solution(String[] gems) {
        
        Set<String> jewel = new HashSet<>(Arrays.asList(gems));    // 보석의 종류
        Map<String, Integer> jewelMap = new HashMap<>();    // 보석 종류별 갯수
        Queue<String> interval = new LinkedList<>();

        int len = Integer.MAX_VALUE;
        int startPoint = 0, answerStartPoint = 0;

        for(int i = 0 ; i < gems.length; i++){
            String gemName = gems[i];

            jewelMap.put(gemName, jewelMap.getOrDefault(gemName, 0)+1);
            interval.offer(gemName);

            while(true){
                String firstGem = interval.peek();

                // 첫번째 보석이 구간안에 또 있는 경우
                if(jewelMap.get(firstGem) > 1){
                    // 첫번째 보석 제거
                    jewelMap.put(firstGem, jewelMap.get(firstGem)-1);
                    interval.poll();

                    startPoint++;
                }
                else
                    break;
            }

            if(jewel.size() == jewelMap.size() && len > interval.size()){
                len = interval.size();
                answerStartPoint = startPoint;
            }
        }

        return new int[]{answerStartPoint+1, answerStartPoint + len};
    }

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] answer = solution(gems);
        
        for(int a : answer){
            System.out.println(a);
        }
    }
}
