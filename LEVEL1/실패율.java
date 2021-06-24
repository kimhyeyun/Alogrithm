
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class 실패율 {
    public static void main(String[] args) {
        int[] stages = {1,2,3,4,5,6,7};
        solution(8, stages);
        
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        // stage 번호와 실패율을 sorting 후에도 매치하기 위해 map 사용
        Map<Integer, Double> failure = new HashMap<>();
        int[] nums = new int[N+1];

        Arrays.sort(stages);
        for(int i=0;i<stages.length;i++){
            // 제일 큰 값이 N+1이 아닌 경우도 있음
            if(stages[i] < N+1)
                nums[stages[i]]++;
        }

        for(int i=1;i<N+1;i++){
            int c  = 0;
            // 앞 스테이지에서 실패한 사람들 제외하기 위해
            for(int j=1;j<i;j++){
                c += nums[j];
            }
            if(stages.length - c == 0){
                failure.put(i, 0.0);
            }
            else{
                double f = (nums[i]*1.0)/(stages.length-c);
                failure.put(i, f);
            }
        }

        // keylist 
        List<Integer> keysetlist = new ArrayList<>(failure.keySet());
        // 내림 차순으로 정렬
        Collections.sort(keysetlist, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return failure.get(o2).compareTo(failure.get(o1));
            }
        });

        answer = new int[keysetlist.size()];
        int i = 0;
        for(int key : keysetlist){
            // answer[i++] = key;
            System.out.println(key+" "+failure.get(key));
        }



        return answer;
    }
}
