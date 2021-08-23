package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;

public class 최대힙 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());

            if(x == 0){
                if(heap.isEmpty()){
                    sb.append(0).append("\n");
                }
                else{
                    sb.append(heap.poll()).append("\n");
                }
            }
            
            else{
                heap.add(x);
            }
        }

        System.out.println(sb);
    }
}
