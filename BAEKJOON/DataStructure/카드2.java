import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 카드2 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();

        for(int i = 1;i <= N;i++){
            deque.offer(i);
        }

        int idx = 1;
        while(deque.size() != 1){
            // 버리는 경우
            if(idx % 2 == 1){
                deque.poll();
            }
            // 맨 밑으로 이동
            else{
                int x = deque.poll();
                deque.offer(x);
            }
            idx++;
        }
        
        System.out.println(deque.peek());
    }
}
