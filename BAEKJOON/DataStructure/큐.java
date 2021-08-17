import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 큐 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new LinkedList<>();

        while(N-- > 0){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            String str = stk.nextToken();

            if(str.equals("push")){
                deque.add(Integer.parseInt(stk.nextToken()));
            }
            else if(str.equals("pop")){
                if(deque.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(deque.pop()).append("\n");
            }
            else if(str.equals("size"))
                sb.append(deque.size()).append("\n");
            else if(str.equals("empty")){
                if(deque.isEmpty())
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            }
            else if(str.equals("front")){
                if(deque.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(deque.peekFirst()).append("\n");
            }
            else if(str.equals("back")){
                if(deque.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(deque.peekLast()).append("\n");
            }
        }

        System.out.println(sb);
    }
}
