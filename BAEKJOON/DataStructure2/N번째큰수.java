package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N번째큰수 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0 ;i < N; i++){
            stringTokenizer = new StringTokenizer(br.readLine());

            while(stringTokenizer.hasMoreTokens())
                pq.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        for(int i = 0 ; i < N-1; i++){
            pq.poll();
        }

        System.out.println(pq.peek());
        
    }    
}
