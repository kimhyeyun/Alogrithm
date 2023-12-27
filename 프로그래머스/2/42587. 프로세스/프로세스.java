import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Collections;

class Solution {
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

}