import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {

    static class job{
        int inTime;
        int jobTime;

        job(int inTime, int jobTime){
            this.inTime = inTime;
            this.jobTime = jobTime;
        }
    }

    public static int solution(int[][] jobs) {
        int answer = 0;

        LinkedList<job> waitingJob = new LinkedList<>();
        PriorityQueue<job> pq = new PriorityQueue<>(new Comparator<job>(){
            @Override
            public int compare(job o1, job o2) {
                // TODO Auto-generated method stub
                return o1.jobTime - o2.jobTime;
            }
        });

        for(int[] j : jobs){
            waitingJob.add(new job(j[0], j[1]));
        }
        Collections.sort(waitingJob, new Comparator<job>(){
            @Override
            public int compare(job o1, job o2) {
                // TODO Auto-generated method stub
                return o1.inTime - o2.inTime;
            }
        });

        int idx = 0;
        int time = waitingJob.peek().inTime;

        while(idx < jobs.length){
            while(!waitingJob.isEmpty() && waitingJob.peek().inTime <= time){
                pq.offer(waitingJob.pollFirst());
            }

            if(!pq.isEmpty()){
                job j = pq.poll();
                time += j.jobTime;
                answer += time - j.inTime;
                idx++;
            }else{
                time++;
            }
        }

        return answer/idx;
        
    }
    public static void main(String[] args) {
        int[][] jobs = {{0,3} , {1,9}, {2,6}};
        System.out.println(solution(jobs));
    }
}
