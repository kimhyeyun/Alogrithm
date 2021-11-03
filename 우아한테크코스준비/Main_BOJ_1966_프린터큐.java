import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1966_프린터큐 {

    static class document{
        int prioirty;
        int idx;

        document(int prioirty, int idx){
            this.prioirty = prioirty;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());
        int N, M;

        while(testCase-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            int ans = 1;

            Queue<document> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            stringTokenizer = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++){
                int p = Integer.parseInt(stringTokenizer.nextToken());
                q.add(new document(p, i));
                pq.add(p);
            }

            while(!pq.isEmpty()){
                if(pq.peek() == q.peek().prioirty){
                    if(q.peek().idx == M){
                        System.out.println(ans);
                        break;
                    }
                    q.poll();
                    pq.poll();
                    ans++;
                }
                else
                    q.add(q.poll());
            }

        }
    }
}
