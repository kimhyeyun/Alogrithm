import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 프린터 {

    /* 
        1. 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냄
        2. 나머지 목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣는다.
        3. 그렇제 않으면 J를 인쇄

        숫자가 클수록 중요하다는 뜻
    */

    public static class pair{
        int priority;
        int location;

        pair(int priority, int location){
            this.priority = priority;
            this.location = location;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<pair> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0;i < priorities.length;i++){
            q.add(new pair( priorities[i],i));
            pq.add(priorities[i]);
        }

        while(!q.isEmpty()){
            if(q.peek().priority == pq.peek()){
                if(q.peek().location == location){
                    return answer+1;
                }
                else{
                    answer++;
                    q.poll();
                    pq.poll();
                }
            }
            else{
                q.add(q.peek());
                q.poll();
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] p = {1,1,9,1,1,1};
        System.out.println(solution(p, 0));
    }
}
