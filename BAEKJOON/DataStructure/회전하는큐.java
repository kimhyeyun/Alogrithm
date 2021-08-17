import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class 회전하는큐 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        LinkedList<Integer> deque = new LinkedList<>();

        int count = 0;
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] idxGrp = new int[M];

        for(int i = 1; i <= N; i++){
            deque.add(i);
        }

        for(int i = 0; i < M; i++){
            idxGrp[i] = sc.nextInt();
        }

        for(int i = 0; i < M; i++){
            int targetIdx = deque.indexOf(idxGrp[i]);
            int halfIdx;
            if(deque.size() % 2 == 0 ){
                halfIdx = deque.size()/2 - 1;
            }
            else{
                halfIdx = deque.size()/2;
            }

            if(targetIdx > halfIdx){
                // 뒤에 것을 앞으로	(3번 연산)
                for(int j = 0; j < deque.size() - targetIdx; j++){
                    deque.addFirst(deque.pollLast());
                    count++;
                }
            }
            else{
                // 앞에 것을 뒤로 (2번 연산)
                for(int j = 0; j < targetIdx; j++){
                    deque.addLast(deque.pollFirst());
                    count++;
                }
            }

            deque.pollFirst();
        }

        System.out.println(count);
    }
}
