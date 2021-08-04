import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 프린터뷰 {

    public static class document{
        int priority;
        int location;

        document(int priority, int location){
            this.priority = priority;
            this.location = location;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int location = sc.nextInt();

            int cnt = 1;
            Queue<document> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for(int j = 0; j < N; j++){
                int p = sc.nextInt();
                q.add(new document(p, j));
                pq.add(p);
            }

            while(!pq.isEmpty()){
                if(pq.peek() == q.peek().priority){
                    if(q.peek().location == location){
                        System.out.println(cnt);
                        break;
                    }
                    q.poll();
                    pq.poll();
                    cnt++;
                }
                else{
                    q.add(q.poll());
                }
            }

        }
    }
}
