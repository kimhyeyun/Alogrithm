import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class 구명보트 {
    // 시간 초과 -> 효율성 테스트 통과 x
   /*  public static int solution(int[] people, int limit) {
        int answer = 0;

        Integer[] p = Arrays.stream(people).boxed().toArray(Integer[]::new);
        Arrays.sort(p, Collections.reverseOrder());

        int start = 0;
        int end = p.length - 1;
        while(start <= end){
            if(p[start] + p[end] > limit){
                answer++;
                start++;
            }
            else{
                start++;
                end--;
                answer++;
            }
        }

        return answer;
    }
 */

    public static int solution(int[] people, int limit){

        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int p : people){
            list.add(p);
        }
        Collections.sort(list);

        // ? 왜 사이즈를 이렇게 지정해야할까
        Deque<Integer> deque = new ArrayDeque<>(50505);
        for(int p : list){
            deque.add(p);
        }

        while(!deque.isEmpty()){
            int weight = deque.pollLast();
            
            if(!deque.isEmpty() && weight + deque.peekFirst() <= limit)
                deque.pollFirst();
            
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] p = {70, 50, 80, 50};

        System.out.println(solution(p, 100));
    }
}
