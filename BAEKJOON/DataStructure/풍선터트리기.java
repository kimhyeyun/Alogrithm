import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class 풍선터트리기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        Deque<int[]> balloon = new ArrayDeque<>();
        sb.append(1+" ");

        int move = sc.nextInt();
        for(int i = 2; i <= N; i++){
            balloon.add(new int[]{i, sc.nextInt()});
        }

        while(!balloon.isEmpty()){
            if(move > 0){
                for(int i = 0 ; i < move-1 ;i++){
                    balloon.add(balloon.pollFirst());
                }
                int[] next = balloon.removeFirst();
                move = next[1];
                sb.append(next[0] + " ");
            }
            else{
                for(int i = move; i < -1; i++) {
                    balloon.addFirst(balloon.pollLast());
                }
                int[] next = balloon.removeLast();
                move = next[1];
                sb.append(next[0] + " ");
            }
        }
        
        System.out.println(sb);
    }
}
