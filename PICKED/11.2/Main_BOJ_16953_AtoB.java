import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_16953_AtoB {
    static long A, B;
    static Set<Long> isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        A = Long.parseLong(stringTokenizer.nextToken());
        B = Long.parseLong(stringTokenizer.nextToken());

        isVisited = new HashSet<>();
        Queue<long[]> queue = new LinkedList<>();
        queue.add(new long[]{A, 0});
        isVisited.add(A);

        while(!queue.isEmpty()){
            long now = queue.peek()[0];
            long cnt = queue.peek()[1];
            queue.poll();

            if(now == B){
                System.out.println(cnt+1);
                return;
            }

            if(now * 2 <= B && !isVisited.contains(now*2)){
                isVisited.add(now*2);
                queue.add(new long[]{now*2, cnt+1});
            }
            
            if(now * 10 + 1 <= B && !isVisited.contains(now*10+1)){
                isVisited.add(now*10+1);
                queue.add(new long[]{now*10+1, cnt+1});
            }
        }
        
        System.out.println(-1);
    }
}
