package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 절대값힙 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                if( Math.abs(o1) > Math.abs(o2) || (Math.abs(o1) == Math.abs(o2) && o1 > o2))
                    return 1;   
                else
                    return -1;
            };
        });

        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());

            if(x == 0){
                if(heap.isEmpty())
                    sb.append(0).append("\n");
                else
                    sb.append(heap.poll()).append("\n");
            }
            else{
                heap.add(x);
            }
        }

        System.out.println(sb);
    }
    
}
